package oodj.assignment.oopskylinecarrentalsystem.util;

import io.github.cdimascio.dotenv.Dotenv;
import oodj.assignment.oopskylinecarrentalsystem.model.Booking;
import oodj.assignment.oopskylinecarrentalsystem.model.Customer;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

public class MailUtils {                //Utilities for Mail Verification Feature
    static Dotenv dotenv;
    static Map<String, String> pendingVerification;

    static {
        dotenv = Dotenv.load();
        pendingVerification = new HashMap<>();
    }

    public static void send(String to, String sub, String body) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(dotenv.get("SENDER_EMAIL"), dotenv.get("GMAIL_APP_PASSWORD"));
            }
        });
        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(Objects.requireNonNull(dotenv.get("SENDER_EMAIL"))));

            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            message.setSubject(sub);

            message.setContent(body, "text/html");

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    private static String generateVerificationCode(String username) {
        Random random = new Random();
        int randomizedToken = random.nextInt(999999);
        String verificationCode = String.format("%06d", randomizedToken);
        pendingVerification.put(username, verificationCode);
        return verificationCode;
    }
    public static void sendVerificationCode(Customer customer) {
        String emailAddress = customer.getEmailAddress();
        String verificationCode = generateVerificationCode(customer.getUsername());

        String title = "Verify Your Email";
        String description = String.format("Skyline Car Rental received a request to use <a style=\"font-weight:bold\">%s</a> as a registration email for an account creation.<br>", emailAddress);
        String keyDescriptor = "Use this code to verify your registration:";
        String action = "If you don&rsquo;t remember yourself registering on Skyline Car Rental, you can safely ignore this email.";
        String reason = "You received this email to let you know about your recent registration on Skyline Car Rental.";
        String htmlBody = generateMailBody(title, description, keyDescriptor, verificationCode, action, reason);

        send(
                emailAddress,
                String.format("Account Registration Email Verification Code: %s", verificationCode),
                htmlBody
        );
    }
    public static boolean validateUserEmail(String username, String code) {
        return code.equals(pendingVerification.get(username));
    }

    public static void sendBookingConfirmation(Customer customer, Booking booking, boolean isConfirmed) {
        String bookingId = String.valueOf(booking.getId());
        String title = "Booking Request Update";
        String description = String.format(
                """
                Skyline Car Rental received your request to book:
                <br>
                %s
                """,
                booking.emailFormat()
        );
        String keyDescriptor = "Your booking request is:";
        String key = isConfirmed ? "CONFIRMED" : String.format("REJECTED & REFUNDED (RM %.2f)", booking.getBookingAmount());
        String action = "If you have any enquiry regarding the booking request, contact us via <a style=\"font-weight:bold\">carrental.skyline66@gmail.com</a>.";
        String reason = "You received this email to update you about your recent booking request on Skyline Car Rental.";
        String htmlBody = generateMailBody(title, description, keyDescriptor, key, action, reason);

        send(
                customer.getEmailAddress(),
                String.format("Booking %s: %s", isConfirmed ? "Confirmation" : "Rejection", bookingId),
                htmlBody
        );
    }

    private static String generateMailBody(String title, String description, String keyDescriptor, String key, String action, String reason) {
        return String.format(
                """
               <table width="100%%" height="100%%" style="min-width:348px" border="0" cellspacing="0" cellpadding="0" lang="en">
                   <tbody>
                       <tr height="32" style="height:32px">
                           <td></td>
                       </tr>
                       <tr align="center">
                           <td>
                               <div>
                                   <div></div>
                               </div>
                               <table border="0" cellspacing="0" cellpadding="0" style="padding-bottom:20px;max-width:516px;min-width:220px">
                                   <tbody>
                                      <tr>
                                         <td width="8" style="width:8px"></td>
                                         <td>
                                            <div style="border-style:solid;border-width:thin;border-color:#dadce0;border-radius:8px;padding:40px 20px" align="center">
                                               <img src="https://drive.google.com/uc?id=1Kizs441ErP6i7W7oEJrzcZ4nEiD8eJlo" width="200" height="200" aria-hidden="true" alt="Google" data-bit="iit">
                                               <div style="font-family:'Google Sans',Roboto,RobotoDraft,Helvetica,Arial,sans-serif;border-bottom:thin solid #dadce0;color:rgba(0,0,0,0.87);line-height:32px;padding-bottom:24px;text-align:center;word-break:break-word">
                                                  <div style="font-size:24px">%s</div>
                                               </div>
                                               <div style="font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:14px;color:rgba(0,0,0,0.87);line-height:20px;padding-top:20px;text-align:left">
                                                  %s
                                                  <br>
                                                  %s
                                                  <br>
                                                  <div style="text-align:center;font-size:36px;margin-top:20px;line-height:44px">%s</div>
                                                  <br>
                                                  %s
                                               </div>
                                            </div>
                                            <div style="text-align:left">
                                               <div style="font-family:Roboto-Regular,Helvetica,Arial,sans-serif;color:rgba(0,0,0,0.54);font-size:11px;line-height:18px;padding-top:12px;text-align:center">
                                                  <div>%s</div>
                                                  <div style="direction:ltr">&copy; 2022 Skyline Car Rental Sdn Bhd., <a style="font-family:Roboto-Regular,Helvetica,Arial,sans-serif;color:rgba(0,0,0,0.54);font-size:11px;line-height:18px;padding-top:12px;text-align:center">Kuala Lumpur, Malaysia</a></div>
                                               </div>
                                            </div>
                                         </td>
                                         <td width="8" style="width:8px"></td>
                                      </tr>
                                   </tbody>
                               </table>
                           </td>
                       </tr>
                       <tr height="32" style="height:32px">
                           <td></td>
                       </tr>
                   </tbody>
               </table>
                """,
                title, description, keyDescriptor, key, action, reason
        );
    }
}