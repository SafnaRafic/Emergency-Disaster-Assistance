package org.SafnaRafic.emergencydisasterhelpline.controllers;


import org.SafnaRafic.emergencydisasterhelpline.models.Inneed;
import org.SafnaRafic.emergencydisasterhelpline.models.data.InneedRepository;
import org.SafnaRafic.emergencydisasterhelpline.models.data.NeededRepository;
import org.SafnaRafic.emergencydisasterhelpline.services.SeekersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;


@Controller
public class PdfExcelController {
    @Autowired
    private InneedRepository inneedRepository;

    // Service for getAllSeekers
    @Autowired
    private SeekersService seekersService;

    // Generate pdf
    @Autowired
    private ServletContext context;

    // Pdf generate controller
    @GetMapping("sponsor")
    public String displaySponsorNeeds(Model model) {
        List<Inneed> inneeds = seekersService.getAllSeekers();
        model.addAttribute("inneeds", inneeds);
        return "inneeds/sponsor";
    }

    // create pdf
    @GetMapping("/createPdf")
    public void createPdf(HttpServletRequest request, HttpServletResponse response) {
        List<Inneed> inneeds = seekersService.getAllSeekers();
        boolean isFlag = seekersService.createPdf(inneeds, context, request, response);
        if (isFlag) {
            String fullPath = request.getServletContext().getRealPath("/resources/reports/"+"seekers"+".pdf");
            filedownload(fullPath, response, "seekers.pdf");
        }

    }

    private void filedownload(String fullPath, HttpServletResponse response, String fileName) {
        File file = new File(fullPath);
        final int BUFFER_SIZE = 4096;
        if (file.exists()) {
            try {
                FileInputStream inputStream = new FileInputStream(file);
                String mimeType = context.getMimeType(fullPath);
                response.setContentType(mimeType);
                response.setHeader("content-disposition", "attachment; filename=" + fileName);
                OutputStream outputStream = response.getOutputStream();
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = -1;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                inputStream.close();
                outputStream.close();
                file.delete();
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }
}
