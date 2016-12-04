/*
 * para change this license header, choose License Headers in Project Properties.
 * para change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.negocio;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.fifa.datos.Tipousuario;
import com.fifa.datos.Usuario;
import java.util.Properties;
import java.util.Random;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
@Stateless
@LocalBean
public class UsuarioSessionBean {

    @PersistenceContext(unitName = "FifaMundial-ejbPU")
    private EntityManager em;

    public static final boolean BLOQUEADO = true;
    public static final boolean DESBLOQUEADO = false;

    //cuent recuperacion de mail
    public static String Username = "fifa.mundial.web@gmail.com";
    public static String PassWord = "alexiagallo";

    public List<Usuario> obtenerUsuario() {
        try {
            Query q = em.createNamedQuery("Usuario.findAll");
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Usuario> obtenerNombre(String nombre) {
        try {
            Query q = em.createNamedQuery("Usuario.findByNombre");
            q.setParameter("nombre", nombre);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean borrarUsuario(int idUsuario) {
        try {
            em.getEntityManagerFactory().getCache().evict(Usuario.class);
            Usuario p = em.find(Usuario.class, idUsuario);
            em.remove(p);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<String> obtenerPass(String nombre, String pass) {
        try {
            Query q = em.createNamedQuery("Usuario.validarPass");
            q.setParameter("nombre", nombre);
            q.setParameter("pass", pass);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public Usuario iniciarSesion(Usuario us) {
        Usuario usuario = null;
        String consulta;
        try {
            consulta = "FROM Usuario u WHERE u.nombre= ?1 and u.contraseña= ?2 and u.bloqueado = 1 ";
            Query query = em.createQuery(consulta);
            query.setParameter(1, us.getNombre());
            query.setParameter(2, us.getContraseña());
            //query.setParameter(3, DESBLOQUEADO);

            List<Usuario> lista = query.getResultList();
            List<String> lista2 = this.obtenerPass(us.getNombre(), us.getContraseña());

            if (!lista.isEmpty()) {
                String passBD = "";
                for (String p : lista2) {
                    passBD = p;
                }
                String contra = us.getContraseña();
                if (passBD.compareTo(contra) == 0) {
                    usuario = lista.get(0);
                }

            }

        } catch (Exception e) {

        }
        return usuario;

    }

    public boolean agregarUsuario(String nombre, String contraseña, int idTipoUsuario) {
        try {
            Tipousuario tipo = em.find(Tipousuario.class, idTipoUsuario);
            if (tipo != null) {
                Usuario u = new Usuario();
                u.setNombre(nombre);
                u.setContraseña(contraseña);
                u.setTipoUsuarioidTipo(tipo);
                em.persist(u);
                em.flush();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean modificarUsuario(int idUsuario, String nombre, String contraseña, Tipousuario tipoUsuarioidTipo) {
        try {
            Usuario p = em.find(Usuario.class, idUsuario);
            p.setNombre(nombre);
            p.setContraseña(contraseña);
            p.setTipoUsuarioidTipo(tipoUsuarioidTipo);
            em.merge(p);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean ConsultaUsuario(int idUsuario, String nombre, String contraseña, Tipousuario tipoUsuarioidTipo) {
        try {
            Usuario p = em.find(Usuario.class, idUsuario);
            p.setNombre(nombre);
            p.setContraseña(contraseña);
            p.setTipoUsuarioidTipo(tipoUsuarioidTipo);
            em.merge(p);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void persist(Object object) {
        em.persist(object);
    }

    public void SendMail(String email, String nombre) {

        String msj1 = "¡Hola " + nombre+" Lamentablemente tu usuario ha sido bloqueado por demasiados intentos de inicio de sesión.";
        String msj2= "Su nueva contraseña es: " + enviarNewPass(nombre);
        String para = email;
        String asunto = "Usuario bloqueado";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Username, PassWord);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(para));
            message.setSubject(asunto);

            Multipart multipart = new MimeMultipart("related");
            BodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent("<img src=\"cid:imagen\"/></body></html>" + "<html><body><h3 align=\"center\">" + msj1 + "</h3><br/>"
                    + "<h3 align=\"center\">" + msj2 + "</h3>"
                    +"<a href=\"http://localhost:8080/FifaMundial-war/faces/CodigoSeguridad.xhtml\">Cambiar contraseña</a>", "text/html");
            //modificar con link real
              
            multipart.addBodyPart(htmlPart);
            
            BodyPart imgPart = new MimeBodyPart();
            DataSource ds = new FileDataSource("F:\\Ing software\\FifaFinal\\FifaMundial-war\\web\\Imagenes\\Menu-Top.jpg");
            imgPart.setDataHandler(new DataHandler(ds));
            imgPart.setHeader("Content-ID", "imagen");
            multipart.addBodyPart(imgPart);
            message.setContent(multipart);

            //message.setContent("<img src=\"cid:Menu-Top.jpg\" alt=\"\"/> <h1>"+msj+"</h1>", "text/html");
            Transport transport = session.getTransport();
            transport.connect();
            transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
            transport.close();

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public String enviarNewPass(String nombre) {
        String pass = "";
        Usuario p = em.find(Usuario.class, nombre);
        pass = p.getContraseña();
        return pass;
    }

    public boolean bloqueoCuenta(String nombre) {
        try {
            Usuario p = em.find(Usuario.class, nombre);
            p.setBloqueado(BLOQUEADO);
            String pass = p.getContraseña();
            p.setPassVieja(pass);
            p.setContraseña(generarPass());
            em.merge(p);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Usuario verEmail(Usuario us) {
        Usuario usuario = null;
        String consulta = "";
        try {
            consulta = "FROM Usuario u WHERE u.nombre= ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, us.getNombre());

            List<Usuario> lista2 = query.getResultList();
            List<String> lista = obtenerEmail(us.getNombre());

            if (!lista.isEmpty()) {
                usuario = lista2.get(0);
            }
        } catch (Exception e) {
        }
        return usuario;
    }

    public List<String> obtenerEmail(String nombre) {
        try {
            Query q = em.createNamedQuery("Usuario.verEmail");
            q.setParameter("nombre", nombre);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public String generarPass() {
        String cadenaAleatoria = "";
        long milis = new java.util.GregorianCalendar().getTimeInMillis();
        Random r = new Random(milis);
        int i = 0;
        while (i < 8) {
            char c = (char) r.nextInt(255);
            if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')) {
                cadenaAleatoria += c;
                i++;
            }
        }
        return cadenaAleatoria;
    }

    public boolean estadoBloqueoUser(String nombre) {
        boolean bloqueado;
        Usuario p = em.find(Usuario.class, nombre);
        bloqueado = p.getBloqueado();
        if (bloqueado == BLOQUEADO) {
            return BLOQUEADO;
        } else {
            return DESBLOQUEADO;
        }
    }
}
