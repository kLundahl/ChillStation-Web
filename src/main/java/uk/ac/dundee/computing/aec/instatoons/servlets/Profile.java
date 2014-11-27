/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instatoons.servlets;

import com.datastax.driver.core.Cluster;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import uk.ac.dundee.computing.aec.instatoons.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instatoons.lib.Convertors;
import uk.ac.dundee.computing.aec.instatoons.models.PicModel;
import uk.ac.dundee.computing.aec.instatoons.models.User;
import uk.ac.dundee.computing.aec.instatoons.stores.LoggedIn;
import uk.ac.dundee.computing.aec.instatoons.stores.Pic;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "Profile", urlPatterns = {
	    "/Profile",
	    "/Profile/*"
})
@MultipartConfig
public class Profile extends HttpServlet {

	private static final long serialVersionUID = 1L;
    private Cluster cluster;
    private HashMap CommandsMap = new HashMap();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
        super();
        // TODO Auto-generated constructor stub
        CommandsMap.put("Profile", 1);
    }

    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String args[] = Convertors.SplitRequestPath(request);
        int command;
        try {
            command = (Integer) CommandsMap.get(args[1]);
        } catch (Exception et) {
            error("Bad Operator", response);
            return;
        }
        switch (command) {
            case 1:
            	restPath(request, response);
                break;
            default:
                error("Bad Operator", response);
        }
    }
    
    private void restPath(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //PicModel tm = new PicModel();
        //tm.setCluster(cluster);
        RequestDispatcher rd = request.getRequestDispatcher("/profile.jsp");
        rd.forward(request, response);

    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private void error(String mess, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = null;
        out = new PrintWriter(response.getOutputStream());
        out.println("<h1>You have an a error in your input</h1>");
        out.println("<h2>" + mess + "</h2>");
        out.close();
        return;
    }

}
