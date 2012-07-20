package org.zkoss.demo.zkFacebook;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MockImage extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 400;
	private static final int HEIGHT = 300;
	private static final int SPACING = 20;
	private Random random = new Random();
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse rep) throws IOException{
		rep.setContentType("image/png");
		ImageIO.write(genImage(req.getParameter("index")), "PNG", rep.getOutputStream());
	}

	private BufferedImage genImage(String parameter) {
		
		BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = bi.createGraphics();
		g.setColor(Color.yellow);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.RED);
		g.setFont(new Font("Arial", Font.BOLD, HEIGHT/2));
		g.drawString(parameter, SPACING, HEIGHT-SPACING);
		return bi;
	}
}
