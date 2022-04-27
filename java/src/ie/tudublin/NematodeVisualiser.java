package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class NematodeVisualiser extends PApplet
{
	ArrayList<Nematode> nematodes = new ArrayList<Nematode>();
	public float halfX = width/2f;
	public float halfY = height/2f;
	int nematodeIndex;

	public void keyPressed()
	{		
		

		if (keyCode == LEFT && nematodeIndex >= 0)
		{
			nematodeIndex--;
			println(nematodeIndex);
			
			if (nematodeIndex==-1)
            {
                nematodeIndex=0;
            }

		}		

		if (keyCode == RIGHT && nematodeIndex < nematodes.size()-1)
		{
			nematodeIndex++;
			print(nematodeIndex);
		}		
	}


	public void settings()
	{
		size(800, 800);
		loadNematodes();
		printNematodes();

		halfX = width/2f;
		halfY = height/2f;

	
		nematodeIndex = 0;
	}

	public void setup() 
	{
		colorMode(HSB);
		background(0);
		smooth();				
	}
	
	

	public void loadNematodes()
	{
		Table table = loadTable("nematodes.csv", "header");
		for (TableRow row : table.rows() ) {

			Nematode myNematode = new Nematode(row);

			nematodes.add(myNematode);

		
		}
	}

	public void printNematodes(){
		for (Nematode t : nematodes ) {
			println(t);
		}

	}

	


	public void draw()
	{	
		background(0);
		nematodes.get(nematodeIndex).render(this);
		
	}
}
