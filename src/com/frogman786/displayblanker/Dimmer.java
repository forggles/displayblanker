package com.frogman786.displayblanker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JWindow;
import javax.swing.UIManager;

public class Dimmer extends JWindow
{
   private static final long serialVersionUID = 3493635987367217622L;

   private final int _screen;

   public Dimmer ()
   {
      this(0);
   }

   public Dimmer (int screen)
   {
      super();
      _screen = screen;

      {
         final JButton button = new JButton("Show");
         button.setForeground(Color.darkGray);
         button.setOpaque(false);
         button.setContentAreaFilled(false);
         button.setBorder(BorderFactory.createEmptyBorder());
         button.addActionListener(new ActionListener()
         {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
               System.exit(0);
            }
         });
         add(button, BorderLayout.CENTER);
      }
      setAlwaysOnTop(true);
   }

   public void begin()
   {
      GraphicsDevice gda[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
      GraphicsDevice gd = gda[_screen];
      getContentPane().setBackground(Color.black);

      for (GraphicsDevice gdTmp : gda)
      {
         System.out.print( (gd == gdTmp) ? "->" : "  ");
         System.out.println( 
                "Screen(" + gdTmp.getDefaultConfiguration().getDevice().getIDstring() +")"
                +" "+ gdTmp.getDefaultConfiguration().getBounds() );
      }

      Rectangle bounds = gd.getDefaultConfiguration().getBounds();
      setLocation(bounds.getLocation());
      setSize(bounds.getSize());

      validate();
      setVisible(true);
   }

   /**
    * @param args
    * @throws Exception 
    */
   public static void main(String[] args) throws Exception
   {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      Dimmer dimmer = new Dimmer(args.length == 1 ? Integer.valueOf(args[0]) : 0);
      dimmer.begin();
   }

}