

import java.awt.*;
import java.applet.*;
import java.util.*;
import java.awt.image.*;
import java.net.*;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Controller.ElementosCPN;
import Model.Arc;
import Model.Place;
import Model.Transition;
import View.AnimatedSprite;
import View.Point2D;


//Comentário de teste de commit e push a partir do eclipse.
//Agora parece que está ok.

public class AnimationClass extends Applet  implements Runnable, KeyListener {
    static int SCREENWIDTH = 640+50;
    static int SCREENHEIGHT = 480+70;
    Thread gameloop;
    
    Rectangle rPlace[];
    String idRPlace[];
    
    int inPlace=0;
    int edgePlace=0;
        
    Random rand = new Random();
    private Image imagePlace[];
    ArrayList<Place> p;
	ElementosCPN eCPN;
    
    int keyCode;
    char keyChar;

    //double buffer objects
    BufferedImage backbuffer;
    Graphics2D g2d;

    Image background;

    //sprite variables
    AnimatedSprite ball;
    
    //places
    Place currentPlacePaint;
    Place currentPlaceInit;
    Place currentPlaceUpdate;
    
    //strings auxiliares dos places in e edge: usando a comparação para encontrar os valores dos arcos pre
    String currentInplace = "";
    String currentEdgeplace = "";
    
    private URL getURL(String filename) {
         URL url = null;
         try {
             url = this.getClass().getResource(filename);
         }
         catch (Exception e) {}
         return url;
    }

    public void init() {
    	
    	eCPN = new ElementosCPN ();		///////referenciar aqui
		p = eCPN.getPlaces();
		imagePlace = new Image[p.size()];
		rPlace = new Rectangle[p.size()];
		idRPlace = new String[p.size()];
		int x = 0, y = 0;
		//Place currentPlace = null;
		currentPlaceInit = null;
		for (int i = 0; i<imagePlace.length; i++){
			currentPlaceInit = (Place) p.get(i);
			System.out.println("("+i+") url: " + currentPlaceInit.getText()+".png");
			//teste
			//imagePlace[i] = this.getImage(getURL(currentPlace.getText()+".png"));
			imagePlace[i] = this.getImage(getURL("Figuras/casa/"+currentPlaceInit.getText()+".png"));
			x = currentPlaceInit.getRelativeLeft()*150+50;	//tamanhos do png
			y = currentPlaceInit.getRelativeTop()*150+70;	//tamanhos do png
			rPlace[i] = new Rectangle(x,y,150,150);
			idRPlace[i] = currentPlaceInit.getId();
		} 
    	
    	addKeyListener(this);
        //create the back buffer for smooth graphics
        backbuffer = new BufferedImage(SCREENWIDTH, SCREENHEIGHT,
            BufferedImage.TYPE_INT_RGB);
        g2d = backbuffer.createGraphics();

        //load the background image
//        Toolkit tk = Toolkit.getDefaultToolkit();
        //background = tk.getImage(getURL("woodgrain.png"));

        //load the ball animation strip
        ball = new AnimatedSprite(this, g2d);
    //    ball.load("mangueira.png", 3, 1, 70, 70);
        ball.load("/Figuras/bombeiro/mangueira.png", 3, 1, 70, 70);
   //     ball.load("D:/Users/Vanessa/Arquivos/UFC/CPNGame/workspace/cpnGame3/mangueira.png", 3, 1, 70, 70);
        ball.setPosition(new Point2D(0,0));
        ball.setFrameDelay(25);

        //testes:
        // x: -395.000000 || y: 249.000000
        // x: -395.000000 || y: 30.000000
        // x: -104.000000 || y: 249.000000
        // x: -104.000000 || y: 30.000000
       
      /*  ImageLabel label = new ImageLabel(new ImageIcon("Figuras/star.png"));
        label.setLocation(200, 200);
        add(label);
    //    labelTeste.setIcon()
     * 
     */
        ball.setVelocity(new Point2D(92.75,0.25));	//posicao inicial do bombeiro
        ball.setRotationRate(0.0);
        
    }
  /*  class ImageLabel extends JLabel {

    	  public ImageLabel(String img) {
    	    this(new ImageIcon(img));
    	  }

    	  public ImageLabel(ImageIcon icon) {
    	    setIcon(icon);
    	    // setMargin(new Insets(0,0,0,0));
    	    setIconTextGap(0);
    	    // setBorderPainted(false);
    	    setBorder(null);
    	    setText(null);
    	    setSize(icon.getImage().getWidth(null), icon.getImage().getHeight(null));
    	  }

    	}*/

    public void start() {
        gameloop = new Thread(this);
        gameloop.start();
    }

    public void stop() {
        gameloop = null;
    }

    public void run() {
        Thread t = Thread.currentThread();
        while (t == gameloop) {
            try {
                Thread.sleep(5);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            gameUpdate();
            repaint();
        }
    }

    public void gameUpdate() {
        //see if it's time to animate
        ball.updateAnimation();
      
        //update the ball position
        ball.updatePosition();
        
        //lastly, update the rotation
        ball.updateRotation();
        
        double x=0;
        double y=0;
        double angle=0;
        
        if (keyCode == KeyEvent.VK_LEFT) {
        	x=-0.5;
        	y=0;
        	angle=90;
        }	
        
        if (keyCode == KeyEvent.VK_RIGHT) {
        	x=0.5;
        	y=0;
        	angle=270;
        }
        
        
        if (keyCode == KeyEvent.VK_UP) {
        	x=0;
        	y=-0.5;
        	angle=180;
        }	
        
        if (keyCode == KeyEvent.VK_DOWN) {
        	x=0;
        	y=0.5;
        	angle=0;
        }
        
        ball.setVelocity(new Point2D(x,y) );
        ball.setFaceAngle(angle);
        
        
       
        
        currentPlaceUpdate = (Place) p.get(0);
        for (int i=0; i<rPlace.length; i++) {
        	currentPlaceUpdate = (Place) p.get(i);
        	
        	if (rPlace[i].contains(ball.getBounds())){
        		
        		System.out.println("inPlace Place = "+currentPlaceUpdate.getText());
        		currentInplace = currentPlaceUpdate.getId();
        		inPlace = i;
        	}	
        	
        	if (rPlace[i].intersects(ball.getBounds())){
        		System.out.println("edgePlace Place = "+currentPlaceUpdate.getText());
        		currentEdgeplace = currentPlaceUpdate.getId();
        		edgePlace = i;
        	}
        	
        	
        	if (inPlace!=edgePlace) {
        		
        		System.out.println("currentInplace 1 = "+currentInplace);
        		
        		String currentTrans="";
        		
        		//PARA DISPARAR:
        		//1o: verificar as fichas presentes nos places de entrada (contando que existe só uma transição de saida para qualquer place da rede)
        		//2o: guardar os arcos de entrada
        		//3o: verificar as condições na transição        		
        		
        		
        		ArrayList<Arc> arcosEntrada = new ArrayList<Arc>();
//        		ArrayList<Arc> arcosSaida = new ArrayList<Arc>();
        		
        		ArrayList<Place> placesEntrada = new ArrayList<Place>();
        		Transition transicaoAnalisada = null;
        		
        		//arcos de entrada da transição
        		for (int trans=0; trans<eCPN.getMatrizPre()[0].length; trans++) {
        			System.out.println("inplace - show PtoT: ["+inPlace+"]["+trans+"] = "+
        		                                   eCPN.getMatrizPre()[inPlace][trans]);
        			//verificar se tem algum caminho da casa para a transição
        			if (eCPN.getMatrizPre()[inPlace][trans]!=0) {
        				//guardar os pres em algum lugar
        				System.out.println("condicao:  ["+inPlace+"]["+trans+"] =" +
        						" "+eCPN.getTransitions().get(trans).getText());
        				currentTrans = eCPN.getTransitions().get(trans).getId();
        				
        				for (int iArc=0; iArc<eCPN.getArcs().size(); iArc++) {
        					String auxTransendArc = eCPN.getArcs().get(iArc).getTransend();
        					String auxPlaceendArc  = eCPN.getArcs().get(iArc).getPlaceend();
        					String auxOrientation  = eCPN.getArcs().get(iArc).getOrientation();
        					
        					System.out.println("currentInplace = "+currentInplace);
        					
        					if (currentTrans.equals(auxTransendArc) &&
        						currentInplace.equals(auxPlaceendArc) &&
        						auxOrientation.equals("PtoT")) {
        						//achou o arco de entrada da transicao
        						
        						System.out.println("condicao de entrada inplace: "+ eCPN.getArcs().get(iArc).getText());
        						//pra amanha: fazer a tabela
        						
        						//1o:
        						placesEntrada.add(currentPlaceUpdate);
        						
        						//2o:
        						//adicionar aos arcos de entrada
        						arcosEntrada.add(eCPN.getArcs().get(iArc));
        						
        						//3o
        						transicaoAnalisada = eCPN.getTransitions().get(trans);
        					}
        				}
        			}
        		}
        		
        		//disparar:
        		//existe alguma condição no arco?
        		boolean existeCondArc = false;
        		for (int b=0; b<arcosEntrada.size() && existeCondArc==false; b++) {
//        			if (arcosEntrada.get(b).getCondicao().) {}
        		}
        		
        		/**
        		//arcos de saida da transição
        		for (int trans=0; trans<eCPN.getMatrizPost()[0].length; trans++) {
        			System.out.println("inplace - show PtoT: ["+inPlace+"]["+trans+"] = "+
        		                                   eCPN.getMatrizPost()[inPlace][trans]);
        			
        			if (eCPN.getMatrizPost()[inPlace][trans]!=0) {
        				//guardar os pres em algum lugar
        				System.out.println("condicao:  ["+inPlace+"]["+trans+"] =" +
        						" "+eCPN.getTransitions().get(trans).getText());
        				currentTrans = eCPN.getTransitions().get(trans).getId();
        				
        				for (int iArc=0; iArc<eCPN.getArcs().size(); iArc++) {
        					String auxTransendArc = eCPN.getArcs().get(iArc).getTransend();
        					String auxPlaceendArc  = eCPN.getArcs().get(iArc).getPlaceend();
        					String auxOrientation  = eCPN.getArcs().get(iArc).getOrientation();
        					
        					System.out.println("currentInplace = "+currentInplace);
        					
        					if (currentTrans.equals(auxTransendArc) &&
        						currentInplace.equals(auxPlaceendArc) &&
        						auxOrientation.equals("TtoP")) {
        						//achou o arco de entrada da transicao
        						
        						System.out.println("condicao de saída inplace: "+ eCPN.getArcs().get(iArc).getText());
        						//pra amanha: fazer a tabela
        						
        						//adicionar aos arcos de entrada
        						arcosSaida.add(eCPN.getArcs().get(iArc));
        					}
        				}
        			}
        		}
        		
        		**/
        		
        		
        		//3o
        		
        		
        		
        		
//        		for (int trans=0; trans<eCPN.getMatrizPre()[0].length; trans++) {
//        			System.out.println("edgeplace - show PtoT: ["+edgePlace+"]["+trans+"] = "+
//        		                                   eCPN.getMatrizPre()[edgePlace][trans]);
//        			
//        			if (eCPN.getMatrizPre()[edgePlace][trans]!=0) {
//        				//guardar os pres em algum lugar
//        				System.out.println(eCPN.getArcs().get(trans).getCondicao());
//        			}
//        		}
        		
        		
        		////Análise de disparo antiga
        		/**
        		boolean permitido = false;
        		        		
        		int [][] placeToPlace = eCPN.getPlaceToPlace();
        		for (int k=0; k< placeToPlace[inPlace].length; k++)
        			 if (placeToPlace[inPlace][k]==edgePlace) {
        				 System.out.println("Permitido de "+inPlace+" Para "+edgePlace);
        				 permitido = true;
        				 break;
        			 }
        		
        		       		
        		
        		if (!permitido)
        			keyCode=0;	
        		
        		**/
        	}
        	
        }	       		
        		
        		
        		
        	
       if ((ball.position().X() < 0) || (ball.position().X() > SCREENWIDTH -70)) {
    	   keyCode=0;
           /*  x = ball.velocity().X() * -1;
            ball.setVelocity(new Point2D(x,0) ); // ball.velocity().Y())
            x = ball.position().X();
            ball.setPosition(new Point2D(x, ball.position().Y())); */
        }
        
        
        if ((ball.position().Y() < 0) || (ball.position().Y() > SCREENHEIGHT - 70)) {
        	keyCode=0;
        	/*  y = ball.velocity().Y() * -1;
            ball.setVelocity(new Point2D(ball.velocity().X(), y));
            y = ball.position().Y() + ball.velocity().Y();
            ball.setPosition(new Point2D(ball.position().X(), y));*/
        }

     
    }

    public void update(Graphics g) {
        //draw the background old
        //g2d.drawImage(background, 0, 0, SCREENWIDTH-1, SCREENHEIGHT-1, this);

        //draw the current frame of animation
        ball.draw();

      //draw the background
        g2d.setColor(Color.WHITE);
        g2d.drawString("Position: " + ball.position().X() + "," +
                       ball.position().Y(), 5, 10);
        g2d.drawString("Velocity: " + ball.velocity().X() + "," +
                       ball.velocity().Y(), 5, 25);
        g2d.drawString("Animation: " + ball.currentFrame(), 5, 40);

        paint(g);
    }


    public void paint(Graphics g) {
        //draw the back buffer to the screen
        g.drawImage(backbuffer, 0, 0, this);
        
        g.drawString("Press a key...", 200, 20);
        g.drawString("Key code: " + keyCode, 200, 50);
        g.drawString("Key char: " + keyChar, 200, 70);
        
        
        g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, getSize().width, getSize().height);
		
		currentPlacePaint = null;
		//Place currentPlace = null;
		int x = 0, y = 0;
		for (int i = 0; i<imagePlace.length; i++){
			currentPlacePaint = (Place) p.get(i);
			x = currentPlacePaint.getRelativeLeft()*150+50;
			y = currentPlacePaint.getRelativeTop()*150+70;
			g2d.drawImage(imagePlace[i], x, y, this);
		}


    }
    
    
    public void keyPressed(KeyEvent e) {
        keyCode = e.getKeyCode();
        keyChar = ' ';
        //repaint();
        System.out.println("Key Presed"+e.getKeyCode());
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
        keyChar = e.getKeyChar();
        //repaint();
        System.out.println("Key Typed"+e.getKeyCode());
    }



}

