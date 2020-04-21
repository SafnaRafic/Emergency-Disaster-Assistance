package org.SafnaRafic.emergencydisasterhelpline.services;

import org.SafnaRafic.emergencydisasterhelpline.models.Inneed;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface SeekersService {
    List<Inneed> getAllSeekers();

    boolean createPdf(List<Inneed> inneeds, ServletContext context, HttpServletRequest request, HttpServletResponse response);
}
