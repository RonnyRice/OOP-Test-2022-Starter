package ie.tudublin;


import java.io.File;

import javax.swing.border.StrokeBorder;

import org.w3c.dom.Text;

import processing.data.TableRow;
import processing.core.PApplet;

public class Nematode {
    
    private String name;
    private int length;
    private boolean limbs;
    private String gender;
    private boolean eyes;
    public float bodyWidth;

    

    public Nematode(String name, int length, Boolean limbs, String gender, boolean eyes) {
        this.name = name;
        this.length = length;
        this.limbs = limbs;
        this.gender = gender;
        this.eyes = eyes;
    }

    
    public Nematode(TableRow tr) {

        this(tr.getString("name"), tr.getInt("length"), tr.getInt("limbs") == 1, tr.getString("gender"), tr.getInt("eyes") == 1);
    }

    @Override
    public String toString() {
        return "Nematode [eyes=" + eyes + ", gender=" + gender + ", length=" + length + ", limbs=" + limbs + ", name="
                + name + "]";
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public boolean getLimbs() {
        return limbs;
    }
    public void setLimbs(Boolean limbs) {
        this.limbs = limbs;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public boolean isEyes() {
        return eyes;
    }
    public void setEyes(boolean eyes) {
        this.eyes = eyes;
    }
    
    public void render(NematodeVisualiser nv){

        
        
        float segmentSize = 40;
        float radius = segmentSize / 2;
        float Vsize = (segmentSize) * 0.75f; 
        float pLength = 20;

        float border = (nv.height - (getLength() * segmentSize)) / 2;
        float y1 = PApplet.map(0, 0, length, border, nv.height - border);;
        float x = nv.width / 2;

        nv.textSize(40);
        nv.textAlign(nv.CENTER, nv.BOTTOM);
        nv.text(this.name, x, y1 - 50);

        nv.noFill();
        //eyes
        if ( this.eyes)
        {
            nv.line(x-15, y1-15, x-30, y1-30);
            nv.line(x+15, y1-15, x+30, y1-30);
            nv.circle(x-32, y1-32, 5);
            nv.circle(x+32, y1-32, 5);
        }

        for (int i = 0; i < getLength(); i++)
        {
           float  y = PApplet.map(i, 0, getLength(), border, nv.height - border);
         


            // worm segment
            nv.circle(x, y, segmentSize);
            // limbs
            if (getLimbs() == true)
            {
                nv.line(x - radius, y, x - radius - 15, y);
                nv.line(x + radius, y, x + radius + 15, y);
            }

           
            //Sex

            if(i == getLength()-1)
            {
            switch(getGender())
            {

                case "m": 
              
                nv.line(x, y + radius, x, y+ radius+pLength);
                nv.circle(x, y + radius + pLength + 5 , 5 );
                
                break;
                case "f": 

                nv.circle(x,y , Vsize );
        
                break;
                case "h": 
                

                nv.circle(x,y , Vsize );
                
                nv.line(x, y + radius, x, y+ radius+pLength);
                nv.circle(x, y + radius + pLength + 4 , 5 );
                break;
               
            }
        }

        }
     
       //arrows
        float y2 = nv.height/2;

        nv.triangle(x-300, y2,x-200,y2-50,x-200,y2+50);
        nv.triangle(x+300, y2,x+200,y2-50,x+200,y2+50);

    }
    
    
}
