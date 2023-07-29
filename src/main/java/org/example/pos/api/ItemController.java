package org.example.pos.api;

import com.google.gson.Gson;
import org.example.pos.dto.ItemDto;
import org.example.pos.service.ItemService;
import org.example.pos.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "Item", urlPatterns = "/item")
public class ItemController extends HttpServlet {
    private ItemService itemService;
    @Override
    public void init() throws ServletException {
       itemService= ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.ITEM);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        int id;
        System.out.println(idParam);
        try{
                id=Integer.parseInt(idParam);
        }catch(NumberFormatException e){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        ItemDto item = itemService.findById(id);
        Gson gson = new Gson();
        String toJson = gson.toJson(item);
        resp.setContentType("application/json");
        resp.getWriter().write(toJson);
    }
    protected void doGetAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ItemDto> all = null;
        try{
            all  = itemService.getAll();
          }catch (NullPointerException e){
              resp.sendError(HttpServletResponse.SC_NO_CONTENT);
          }
        Gson gson = new Gson();
        if (all != null) {
            String toJson = gson.toJson(all);
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            writer.write(toJson);
            writer.flush();

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ItemDto itemDto = getItem(req);
        System.out.println(req);
        System.out.println(itemDto);
        ItemDto save = itemService.save(itemDto);
        if (save != null){
            Gson gson = new Gson();
            String toJson = gson.toJson(save);
            resp.setContentType("application/json");
            resp.getWriter().write(toJson);
            resp.setStatus(HttpServletResponse.SC_CREATED);
        }else {
        resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        System.out.println(req+" "+id);
        boolean update = itemService.update(getItem(req));
        if (update ) {
            resp.setStatus(HttpServletResponse.SC_OK);
        }else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        boolean delete = itemService.delete(Integer.parseInt(req.getParameter("id")));
        if (delete){
            resp.setStatus(HttpServletResponse.SC_OK);
        }else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }


    public ItemDto getItem(HttpServletRequest req)  {
        Gson gson = new Gson();
        StringBuilder builder = new StringBuilder();


        try {
            BufferedReader reader = req.getReader();
            String line;
            while ((line = reader.readLine()) != null){
                builder.append(line);
            }
            System.out.println("From Get Item"+builder.toString());
            return gson.fromJson(builder.toString(), ItemDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
