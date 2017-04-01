package file.download;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileDownResource {

    // 文件下载
    @RequestMapping(value="downloadFile", method=RequestMethod.GET, produces="text/html;charset=utf-8")
    @ResponseBody
    public void downloadFile(HttpServletRequest req, HttpServletResponse response) throws IOException {

        OutputStream out=response.getOutputStream();

        InputStream in=new FileInputStream("D://123.mp3");// 文件输入流

        // response.setContentType("application/x-msdownload");
        response.setContentType("application/octet-stream");
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("123.mp3", "UTF-8"));

        try {
            int tempbyte;
            while((tempbyte=in.read()) != -1) {
                out.write(tempbyte);
            }
            in.close();
            out.flush();
        } catch(IOException e) {
            e.printStackTrace();

        }
    }
}
