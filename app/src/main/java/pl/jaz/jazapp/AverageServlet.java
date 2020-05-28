package pl.jaz.jazapp;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@WebServlet("average")
public class AverageServlet extends HttpServlet  {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var parameter = req.getParameter("numbers");
        resp.setStatus(200);
        resp.setContentType("text/plain");
        var writer = resp.getWriter();

        if (parameter!=null) {
            var numbers = parameter.split(",");
            float sum = 0;
            for (String number : numbers) {
                sum += Integer.parseInt(number);

            }
            float avg = sum/(numbers.length);

            BigDecimal bd = new BigDecimal(Float.toString(avg));
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            String result = bd.toString();

            String finalResult = zeroChecker(result);
            writer.println("Average equals: " + finalResult);
        }
        else {
            writer.println("Please put parameters.");
        }
    }

    private String zeroChecker(String result){
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<result.length() ; i++) {
            char character = result.charAt(i);
            if (character=='.'){
                if (result.charAt(i+1)=='0'){
                    if (result.charAt(i+2)=='0') {
                        return sb.toString();
                    }
                }
            }
            if (i==result.length()-1 && result.charAt(i)=='0'){
                return sb.toString();
            }
            sb.append(character);
        }
        return sb.toString();
    }
}