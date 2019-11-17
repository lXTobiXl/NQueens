/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author HSM & Tobi
 */
public class NQueens extends JFrame {

    String title, iconPath;
    int width, height;
    JButton boardButtons[][];
    int boardSize;
    int buttonSize;
    int arrayInt[][];
    boolean done;
    JPanel boardPanel;
    URL iconResource;
    int ver;
    ImageIcon queenIcon;

    public NQueens(int v) {
        done = false;
        ver = v;
        width = 600;
        height = 600;
        title = "N Queens Game";
        iconPath = "/view/black-queen-2d-icon.png";
        boardSize = 8;
        buttonSize = (height / boardSize);
        this.setSize(width, height);
        this.setLocationRelativeTo(this);
        this.setResizable(false);
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(boardSize, boardSize));
        boardButtons = new JButton[boardSize][boardSize];
        arrayInt = new int[boardSize][boardSize];
        iconResource = NQueens.class.getResource(iconPath);
        queenIcon = new ImageIcon("black-queen-2d-icon.png");
        for (int i = 0; i < boardButtons.length; i++) {
            for (int j = 0; j < boardButtons[i].length; j++) {
                arrayInt[i][j] = -1;
                boardButtons[i][j] = new JButton();
                boardButtons[i][j].setSize(buttonSize, buttonSize);
                boardPanel.add(boardButtons[i][j]);
                int ii = i, jj = j;
                boardButtons[i][j].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    @Override
                    public void mousePressed(MouseEvent e) { //When you still press mouse click
                     
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        //When you leave mouse click 
                        if (!done&&ver==1) {
                            initSolve(ii,jj);
                            render();
                            done=true;
                            for(int i=0;i<arrayInt.length;++i)
                            {
                                for(int x=0;x<arrayInt.length;++x)
                                          System.out.print(arrayInt[i][x]);
                                System.out.println("");
                            }
                        }
                        if(ver==2)
                        {
                            if (SwingUtilities.isLeftMouseButton(e)&&arrayInt[ii][jj]==-1) {
                                   ++arrayInt[ii][jj];
                                play(ii, jj);
                            } else if (SwingUtilities.isRightMouseButton(e)&&arrayInt[ii][jj]>-1) {
                                arrayInt[ii][jj] = -1;
                              play(ii,jj);
                            }
                            System.out.println(arrayInt[ii][jj]);
                        }
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) { //When you touch a button by your mouse

                    }

                    @Override
                    public void mouseExited(MouseEvent e) { //When you leave a button by your mouse

                    }

                });

            }
        }

        this.add(boardPanel);
        this.setTitle(title);
      //  this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.render();
        this.setVisible(true);
          this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
               controller.MainStart.m.setVisible(true);
                e.getWindow().dispose();
            }
        });

    }

    public boolean play(int x, int y) {
        evaluate(x,y);
        render();
        if (isWin()) {
            JOptionPane.showConfirmDialog(null, "WOW", "You made it.", JOptionPane.PLAIN_MESSAGE);
            done=true;
        }
        return true;
    }
    int fix;
        public void initSolve(int x,int y)
        {
            arrayInt[x][y]=0;
            fix=x;
            System.out.println("here "+solve(0)); 
        }
    public boolean solve(int curr ) {
        System.out.println(curr);
        if(curr>7)
            return true;
        else if(curr==fix)
            return solve(curr+1);
      int i=0;
      while(i<8)
      {
      //    System.out.println(curr+" "+fix);
      //    System.out.println(i+" "+curr);
     
          arrayInt[curr][i]=0;
          if(evaluate(curr, i)&&solve(curr+1))
          {
                  return true;
          }else
          {
              arrayInt[curr][i]=-1;
              evaluate(curr, i);
          }
          ++i;
         
     //     System.out.println(i);
      }
        return false;
    }

    public boolean evaluate(int x, int y) {
boolean ret=true;
        int mvx[] = {1, 1, 1, 0, 0, -1, -1, -1};
        int mvy[] = {1, 0, -1, 1, -1, 1, -1, 0};
        int nx, ny;
        for (int it = 0; it < 8; ++it) {
            nx = x + mvx[it];
            ny = y + mvy[it];
            while (nx < boardSize && nx > -1 && ny < boardSize && ny > -1) {
                if (arrayInt[nx][ny] > -1) {
                    if (arrayInt[x][y] > -1) {
                        ++arrayInt[nx][ny];
                        ++arrayInt[x][y];
                        ret=false;
                    } else {
                        --arrayInt[nx][ny];
                    }

                }
                nx += mvx[it];
                ny += mvy[it];
            }
        }
return ret;
    }

    public boolean render() {
        for (int arrayIndex = 0; arrayIndex < boardSize; arrayIndex++) {
            for (int arrayIndex2 = 0; arrayIndex2 < boardSize; arrayIndex2++) {
                boardButtons[arrayIndex][arrayIndex2].setIcon(null);
                boardButtons[arrayIndex][arrayIndex2].setBackground(null);
                if (arrayIndex % 2 == 0) {
                    if (arrayIndex2 % 2 == 0) {
                        boardButtons[arrayIndex][arrayIndex2].setBackground(Color.DARK_GRAY);
                    } else {
                        boardButtons[arrayIndex][arrayIndex2].setBackground(Color.WHITE);
                    }

                } else {
                    if (arrayIndex2 % 2 == 0) {
                        boardButtons[arrayIndex][arrayIndex2].setBackground(Color.WHITE);
                    } else {
                        boardButtons[arrayIndex][arrayIndex2].setBackground(Color.DARK_GRAY);
                    }
                }
                if(arrayInt[arrayIndex][arrayIndex2]>0)
                {
                    boardButtons[arrayIndex][arrayIndex2].setBackground(Color.red);
                        boardButtons[arrayIndex][arrayIndex2].setIcon(new ImageIcon(iconResource));
                }else if(arrayInt[arrayIndex][arrayIndex2]==0)
                    boardButtons[arrayIndex][arrayIndex2].setIcon(new ImageIcon(iconResource));
            }
        }
        return true;
    }

    public boolean isWin() {
        boolean win = true;
        for (int arrayIndex = 0; arrayIndex < boardSize; arrayIndex++) {
            boolean here = false;
            for (int arrayIndex2 = 0; arrayIndex2 < boardSize; arrayIndex2++) {
                if (arrayInt[arrayIndex][arrayIndex2] > 0) {
                    win = false;
                } else if (arrayInt[arrayIndex][arrayIndex2] == 0) {
                    here = true;
                }
            }
            if (!here) {
                win = false;
            }
        }
        return win;
    }
}
