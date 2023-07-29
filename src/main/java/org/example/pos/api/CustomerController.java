package org.example.pos.api;

import com.google.gson.Gson;
import org.example.pos.dto.CustomerDto;
import org.example.pos.dto.ItemDto;
import org.example.pos.service.CustomerService;
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

@WebServlet(urlPatterns = "/customer")
public class CustomerController extends HttpServlet {
    private CustomerService customerService= ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.CUSTOMER);

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

        CustomerDto customerDto = customerService.findById(id);
        Gson gson = new Gson();
        String toJson = gson.toJson(customerDto);
        resp.setContentType("application/json");
        resp.getWriter().write(toJson);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        System.out.println(req+" "+id);
        boolean update = customerService.update(getCustomer(req));
        if (update ) {
            resp.setStatus(HttpServletResponse.SC_OK);
        }else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDto customerDto = getCustomer(req);
        System.out.println(req);
        System.out.println(customerDto);
        CustomerDto save = customerService.save(customerDto);
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
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean delete = customerService.delete(Integer.parseInt(req.getParameter("id")));
        if (delete){
            resp.setStatus(HttpServletResponse.SC_OK);
        }else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public CustomerDto getCustomer(HttpServletRequest req)  {
        Gson gson = new Gson();
        StringBuilder builder = new StringBuilder();


        try {
            BufferedReader reader = req.getReader();
            String line;
            while ((line = reader.readLine()) != null){
                builder.append(line);
            }
            System.out.println("From Get Item"+builder.toString());
            return gson.fromJson(builder.toString(), CustomerDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    protected void doGetAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CustomerDto> all = null;
        try{
            all  = customerService.getAll();
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

}
