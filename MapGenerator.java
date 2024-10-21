package MapGenerator;
import  java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;


interface Map{
    public void draw(Graphics2D g);
    public void setBrickValue(int value,int r,int c);
}
public class MapGenerator implements  Map{
    public int map[][];
    public int brickwidth,brickheight;
    // constructor
    public MapGenerator(int r,int c){
        map=new int[r][c];
        for (int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                map[i][j]=1;  // this has not inersected with ball
            }
        }
        brickwidth=540/c;
        brickheight=150/r;
    }
    public void draw(Graphics2D g){
        // raw bricks where value=1 i.e. brick is not intersected
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                if(map[i][j]>0){
                    g.setColor(Color.white);
                    g.fillRect(j*brickwidth+80, i*brickheight+50, brickwidth, brickheight);

                    // to draw borders of block 
                    g.setStroke(new BasicStroke(1));
                    g.setColor(Color.black);
                    g.drawRect(j*brickwidth+80, i*brickheight+50, brickwidth, brickheight);
                }
            }
        }
    }
    // to set value to 0 if brick intersect ball
    // to set value 1 if brick does not intersect the ball
    public void setBrickValue(int value,int r,int c){
        map[r][c]=value;
    }
}