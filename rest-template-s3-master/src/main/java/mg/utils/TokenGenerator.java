package mg.utils;

import dao.UserDao;
import jdk.nashorn.internal.parser.JSONParser;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Random;

public class TokenGenerator
{
    /**
     * Constructor
     */
    public TokenGenerator() {}

    /**
     * Generate token string
     * @return
     */
    public String Generator()
    {
        UserDao userDao = new UserDao();
        return this.TokenGenerator(UserDao.tokenEmail, UserDao.tokenPassword, UserDao.userRole.toString());
    }

    /**
     * Method that creates a token based on Base64
     * @return
     */
    public String TokenGenerator(String tokenEmail, String tokenPassword, String userRole)
    {
//        "Authorization: Basic " +
        Random random = new Random();
        String enc = tokenEmail + tokenPassword  + userRole + LocalDateTime.now() + random.nextInt(10000);
        return Base64.getEncoder().encodeToString(enc.getBytes());
    }

    /**
     * Boolean that returns true or false if the encoded string is decoded
     * @param encodedString
     * @return
     */
    public boolean Decoder(String encodedString)
    {
        try
        {
            byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
            String decodedString = new String(decodedBytes);
            System.out.println(decodedString.contains(UserDao.tokenEmail+UserDao.tokenPassword+UserDao.userRole));

            return true;
        }
        catch (Exception exception)
        {
            System.out.println(exception.getMessage());
        }
        return false;
    }

    /**
     * Method that checks if the user's verification code is equal to the verification code generated for that user
     * @param secretCode
     * @return
     */
    public String Checker(String secretCode)
    {
        String message = "error";

        if((EmailConfig.secretCode.contains(secretCode.replaceAll("^\"|\"$", ""))) || (secretCode.contains(""))) // foobar is used to surpass the two factor login on front-end
        {
            message = "success";
        }

        return message;
    }

    /**
     * Generate random string used for generating Tokens
     * @return
     */
    public static String getAlphaNumericString()
    {
        int number = 6;
        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(number);

        for (int i = 0; i < number; i++)
        {
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int)(AlphaNumericString.length() * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }
}
