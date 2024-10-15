// 요청 핸들러의 아규먼트 - @RequestBody : 클라이언트가 보낸 데이터를 한 덩어리로 받기
package bitcamp.app1;

import com.google.gson.Gson;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller 
@RequestMapping("/c04_9")
public class Controller04_9 {

  // 클라이언트가 보낸 데이터를 통째로 받기
  // => request handler의 아규먼트 앞에 @RequestBody를 붙이면 된다.
  
  // 테스트:
  //    http://.../html/app1/c04_9.html
  @PostMapping(value="h1", produces="text/html;charset=UTF-8") 
  @ResponseBody 
  public String handler1(
      // 클라이언트가 보낸 데이터를 통째로 받으려면 @RequestBody 애노테이션을 붙인다.
      @RequestBody String jsonData
      ) throws Exception {

    Car car = new Gson().fromJson(jsonData, Car.class);

    StringWriter out0 = new StringWriter();
    PrintWriter out = new PrintWriter(out0);
    out.println("<html><head><title>c04_9/h1</title></head><body>");
    out.println("<h1>결과</h1>");
    out.printf("<p>model:%s</p>\n", car.model);
    out.printf("<p>cylinder%s</p>\n", car);
    out.printf("<p>통데이터:%s</p>\n", jsonData);
    out.println("</body></html>");
    return out0.toString();
  }

}










