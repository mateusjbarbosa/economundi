package com.backend.economundi.controller;

import com.backend.economundi.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("api/v1")
public class SendEmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "public/testSendEmail", method = RequestMethod.GET)
    public ResponseEntity sendEmail(String emailTo) {

        String message = "<!DOCTYPE html\n"
                + "    PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                + "\n"
                + "<head>\n"
                + "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n"
                + "    <title>Economundi</title>\n"
                + "    <!-- Designed by https://github.com/kaytcat -->\n"
                + "    <!-- Robot header image designed by Freepik.com -->\n"
                + "\n"
                + "    <style type=\"text/css\">\n"
                + "        @import url(http://fonts.googleapis.com/css?family=Droid+Sans);\n"
                + "\n"
                + "        /* Take care of image borders and formatting */\n"
                + "\n"
                + "        img {\n"
                + "            max-width: 600px;\n"
                + "            outline: none;\n"
                + "            text-decoration: none;\n"
                + "            -ms-interpolation-mode: bicubic;\n"
                + "        }\n"
                + "\n"
                + "        a {\n"
                + "            text-decoration: none;\n"
                + "            border: 0;\n"
                + "            outline: none;\n"
                + "            color: #bbbbbb;\n"
                + "        }\n"
                + "\n"
                + "        a img {\n"
                + "            border: none;\n"
                + "        }\n"
                + "\n"
                + "        /* General styling */\n"
                + "\n"
                + "        td,\n"
                + "        h1,\n"
                + "        h2,\n"
                + "        h3 {\n"
                + "            font-family: Helvetica, Arial, sans-serif;\n"
                + "            font-weight: 400;\n"
                + "        }\n"
                + "\n"
                + "        td {\n"
                + "            text-align: center;\n"
                + "        }\n"
                + "\n"
                + "        body {\n"
                + "            -webkit-font-smoothing: antialiased;\n"
                + "            -webkit-text-size-adjust: none;\n"
                + "            width: 100%;\n"
                + "            height: 100%;\n"
                + "            color: #37302d;\n"
                + "            background: #ffffff;\n"
                + "            font-size: 16px;\n"
                + "        }\n"
                + "\n"
                + "        table {\n"
                + "            border-collapse: collapse !important;\n"
                + "        }\n"
                + "\n"
                + "        .headline {\n"
                + "            color: #ffffff;\n"
                + "            font-size: 36px;\n"
                + "        }\n"
                + "\n"
                + "        .force-full-width {\n"
                + "            width: 100% !important;\n"
                + "        }\n"
                + "\n"
                + "        .force-width-80 {\n"
                + "            width: 80% !important;\n"
                + "        }\n"
                + "    </style>\n"
                + "\n"
                + "    <style type=\"text/css\" media=\"screen\">\n"
                + "        @media screen {\n"
                + "\n"
                + "            /*Thanks Outlook 2013! http://goo.gl/XLxpyl*/\n"
                + "            td,\n"
                + "            h1,\n"
                + "            h2,\n"
                + "            h3 {\n"
                + "                font-family: 'Droid Sans', 'Helvetica Neue', 'Arial', 'sans-serif' !important;\n"
                + "            }\n"
                + "        }\n"
                + "    </style>\n"
                + "\n"
                + "    <style type=\"text/css\" media=\"only screen and (max-width: 480px)\">\n"
                + "        /* Mobile styles */\n"
                + "        @media only screen and (max-width: 480px) {\n"
                + "\n"
                + "            table[class=\"w320\"] {\n"
                + "                width: 320px !important;\n"
                + "            }\n"
                + "\n"
                + "            td[class=\"mobile-block\"] {\n"
                + "                width: 100% !important;\n"
                + "                display: block !important;\n"
                + "            }\n"
                + "\n"
                + "\n"
                + "        }\n"
                + "    </style>\n"
                + "</head>\n"
                + "\n"
                + "<body class=\"body\" style=\"padding:0; margin:0; display:block; background:#ffffff; -webkit-text-size-adjust:none\"\n"
                + "    bgcolor=\"#ffffff\">\n"
                + "    <table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" class=\"force-full-width\" height=\"100%\">\n"
                + "        <tr>\n"
                + "            <td align=\"center\" valign=\"top\" bgcolor=\"#ffffff\" width=\"100%\">\n"
                + "                <center>\n"
                + "                    <table style=\"margin: 0 auto;\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" class=\"w320\">\n"
                + "                        <tr>\n"
                + "                            <td align=\"center\" valign=\"top\">\n"
                + "\n"
                + "                                <table style=\"margin: 0 auto;\" cellpadding=\"0\" cellspacing=\"0\" class=\"force-full-width\"\n"
                + "                                    style=\"margin:0 auto;\">\n"
                + "                                    <tr>\n"
                + "                                        <td style=\"font-size: 30px; text-align:center;\">\n"
                + "                                            Portal EconoMundi\n"
                + "                                        </td>\n"
                + "                                    </tr>\n"
                + "                                </table>\n"
                + "\n"
                + "                                <table style=\"margin: 0 auto;\" cellpadding=\"0\" cellspacing=\"0\" class=\"force-full-width\"\n"
                + "                                    bgcolor=\"#4dbfbf\">\n"
                + "                                    <tr>\n"
                + "                                        <td>\n"
                + "                                            <br>\n"
                + "                                            <img src=\"https://www.filepicker.io/api/file/TjmeNWS5Q2SFmtJlUGLf\"\n"
                + "                                                width=\"224\" height=\"240\" alt=\"robot picture\">\n"
                + "                                        </td>\n"
                + "                                    </tr>\n"
                + "                                    <tr>\n"
                + "                                        <td class=\"headline\">\n"
                + "                                            Seu Cadastro Foi Realizado Com Sucesso!\n"
                + "                                        </td>\n"
                + "                                    </tr>\n"
                + "                                    <tr>\n"
                + "                                        <td>\n"
                + "\n"
                + "                                            <center>\n"
                + "                                                <table style=\"margin: 0 auto;\" cellpadding=\"0\" cellspacing=\"0\"\n"
                + "                                                    width=\"60%\">\n"
                + "                                                    <tr>\n"
                + "                                                        <td style=\"color:#187272;\">\n"
                + "                                                            <br>\n"
                + "                                                            Obrigado por se Cadastrar.\n"
                + "                                                            <br>\n"
                + "                                                            <br>\n"
                + "                                                        </td>\n"
                + "                                                    </tr>\n"
                + "                                                </table>\n"
                + "                                            </center>\n"
                + "\n"
                + "                                        </td>\n"
                + "                                    </tr>\n"
                + "                                    <tr>\n"
                + "                                        <td>\n"
                + "                                            <div>\n"
                + "\n"
                + "                                                <a href=\"http://localhost:3000\"\n"
                + "                                                    style=\"background-color:#178f8f;border-radius:4px;color:#ffffff;display:inline-block;font-family:Helvetica, Arial, sans-serif;font-size:16px;font-weight:bold;line-height:50px;text-align:center;text-decoration:none;width:200px;-webkit-text-size-adjust:none;\">\n"
                + "                                                    Voltar ao Portal</a>\n"
                + "\n"
                + "                                            </div>\n"
                + "                                            <br>\n"
                + "                                            <br>\n"
                + "                                        </td>\n"
                + "                                    </tr>\n"
                + "                                </table>\n"
                + "\n"
                + "\n"
                + "\n"
                + "                            </td>\n"
                + "                        </tr>\n"
                + "                    </table>\n"
                + "                </center>\n"
                + "            </td>\n"
                + "        </tr>\n"
                + "    </table>\n"
                + "</body>\n"
                + "\n"
                + "</html>";

        try {
            emailService.sendMail(emailTo, "Portal EconoMundi", message);
        } catch (MessagingException e) {
            e.printStackTrace();

        }

        return new ResponseEntity("Email Enviado Com Sucesso", HttpStatus.OK);
    }

}
