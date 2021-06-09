package tetris;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TetrisAnimation2 extends JApplet implements Runnable,KeyListener{
	int x1=150;
	int y1;
	int x2,y2,x3,y3,x4,y4;
	int downCount=0;
	int key;
	int pattern=0;
	int kaitenCount=0;
	int colorNum=0;
	boolean gameOver=false;
	static int multi;
	static int score;
	static int[][] banmen=new int[19][10];
	Color color;
	Color[] cols= {Color.DARK_GRAY,Color.GREEN,Color.ORANGE,Color.RED,Color.MAGENTA};
	Dimension size;
	Image back;
	Graphics b;
	public void init() {
		//縦19横10マス
		for(int i=0;i<19;i++) {
			for(int j=0;j<10;j++) {
				banmen[i][j]=0;
			}
		}
		addKeyListener(this);
		resize(560,1000);
		size=getSize();
		back=createImage(size.width, size.height);
		b=back.getGraphics();
		Thread thread=new Thread(this);
		thread.start();
	}
	public void paint(Graphics g) {
		
		b.setColor(Color.BLUE);
		b.fillRect(0, 0, 1000, 1000);
		b.setColor(Color.white);
		b.drawString("Hello",20,20);
		b.drawLine(360, 30, 560, 30);
		b.drawLine(360, 230, 560, 230);
		b.drawString("next:", 370, 60);
		sampleMino(g);
		if(multi>0) {
			score+=Math.pow(3, multi)*100;
		}
		b.drawString("score:"+String.valueOf(score),280,20);
		for(int i=0;i<20;i++) {
			b.drawRect(0, 30+i*30, 30, 30);
			b.drawRect(330, 30+i*30, 30, 30);
			b.drawRect(30+i*30, 600, 30, 30);
		}
		for(int i=0;i<19;i++) {
			for(int j=0;j<10;j++) {
				if(banmen[i][j]==1) {
					b.setColor(Color.GRAY);
					b.fillRect((j+1)*30,(i+1)*30,30,30);
					b.setColor(Color.BLACK);
					b.drawRect((j+1)*30,(i+1)*30,30,30);
					
				}
			}
		}
		minoPattern();
		createMino(g);
		b.drawString(String.valueOf(downCount),100,100);
		if(gameOver) {
			b.setColor(Color.white);
			b.drawString("GAMEOVER", 150, 200);
			b.drawString("score", 150, 210);
			b.drawString(String.valueOf(score), 150, 220);
		}
		multi=0;
		g.drawImage(back,0,0,this);
		requestFocusInWindow();
	}
	public void run() {
		while(true) {
			okihantei();
			repaint();
			try {
				Thread.sleep(250);
			}catch(InterruptedException e) {
			}
			downCount++;
		}
	}
	public void createMino(Graphics g) {
			b.setColor(cols[colorNum]);
			b.fillRect(x1, y1, 30, 30);
			b.fillRect(x2, y2, 30, 30);
			b.fillRect(x3, y3, 30, 30);
			b.fillRect(x4, y4, 30, 30);
			b.setColor(Color.black);
			b.drawRect(x1, y1, 30, 30);
			b.drawRect(x2, y2, 30, 30);
			b.drawRect(x3, y3, 30, 30);
			b.drawRect(x4, y4, 30, 30);
	}
	public void minoPattern() {
		//横棒
		if(pattern==0) {
			if(kaitenCount%2==0) {
				x2=x1-30;
				x3=x1+30;
				x4=x1-60;
				y1=downCount*30;
				y2=downCount*30;
				y3=downCount*30;
				y4=downCount*30;
			}else if(kaitenCount%2==1) {
				x2=x1;
				x3=x1;
				x4=x1;
				y1=downCount*30;
				y2=downCount*30-30;
				y3=downCount*30-60;
				y4=downCount*30-90;
			}
		}
		//L字
		if(pattern==1) {
			if(kaitenCount%4==0) {
				x2=x1-30;
				x3=x1+30;
				x4=x1+30;
				y1=downCount*30;
				y2=downCount*30;
				y3=downCount*30;
				y4=downCount*30-30;
			}else if(kaitenCount%4==1) {
				x2=x1;
				x3=x1;
				x4=x1+30;
				y1=downCount*30;
				y2=downCount*30-60;
				y3=downCount*30-30;
				y4=downCount*30;
			}else if(kaitenCount%4==2) {
				x2=x1;
				x3=x1+30;
				x4=x1+60;
				y1=downCount*30;
				y2=downCount*30-30;
				y3=downCount*30-30;
				y4=downCount*30-30;
			}else if(kaitenCount%4==3) {
				x2=x1;
				x3=x1;
				x4=x1+30;
				y1=downCount*30;
				y2=downCount*30-30;
				y3=downCount*30-60;
				y4=downCount*30-60;
			}
		}
		//凸字
		if(pattern==2) {
			if(kaitenCount%4==0) {
				x2=x1-30;
				x3=x1+30;
				x4=x1;
				y1=downCount*30;
				y2=downCount*30;
				y3=downCount*30;
				y4=downCount*30-30;
			}else if(kaitenCount%4==1) {
				x2=x1;
				x3=x1+30;
				x4=x1;
				y1=downCount*30;
				y2=downCount*30-30;
				y3=downCount*30-30;
				y4=downCount*30-60;
			}else if(kaitenCount%4==2) {
				x2=x1;
				x3=x1+30;
				x4=x1-30;
				y1=downCount*30;
				y2=downCount*30-30;
				y3=downCount*30-30;
				y4=downCount*30-30;
			}else if(kaitenCount%4==3) {
				x2=x1;
				x3=x1-30;
				x4=x1;
				y1=downCount*30;
				y2=downCount*30-30;
				y3=downCount*30-30;
				y4=downCount*30-60;
			}
		}
		//四角
		if(pattern==3) {
			x2=x1-30;
			x3=x1-30;
			x4=x1;
			y1=downCount*30;
			y2=downCount*30;
			y3=downCount*30-30;
			y4=downCount*30-30;
		}
		//S字
		if(pattern==4) {
			if(kaitenCount%2==0) {
				x2=x1+30;
				x3=x1;
				x4=x1-30;
				y1=downCount*30;
				y2=downCount*30;
				y3=downCount*30-30;
				y4=downCount*30-30;
			}else if(kaitenCount%2==1) {
				x2=x1;
				x3=x1+30;
				x4=x1+30;
				y1=downCount*30;
				y2=downCount*30-30;
				y3=downCount*30-30;
				y4=downCount*30-60;
			}
		}
		//逆S字
		if(pattern==5) {
			if(kaitenCount%2==0) {
				x2=x1-30;
				x3=x1;
				x4=x1+30;
				y1=downCount*30;
				y2=downCount*30;
				y3=downCount*30-30;
				y4=downCount*30-30;
			}else if(kaitenCount%2==1) {
				x2=x1;
				x3=x1-30;
				x4=x1-30;
				y1=downCount*30;
				y2=downCount*30-30;
				y3=downCount*30-30;
				y4=downCount*30-60;
			}
		}
		//逆L字
		if(pattern==6) {
			if(kaitenCount%4==0) {
				x2=x1-30;
				x3=x1-30;
				x4=x1+30;
				y1=downCount*30;
				y2=downCount*30-30;
				y3=downCount*30;
				y4=downCount*30;
			}else if(kaitenCount%4==1) {
				x2=x1;
				x3=x1;
				x4=x1+30;
				y1=downCount*30;
				y2=downCount*30-60;
				y3=downCount*30-30;
				y4=downCount*30;
			}else if(kaitenCount%4==2) {
				x2=x1;
				x3=x1+30;
				x4=x1+60;
				y1=downCount*30;
				y2=downCount*30-30;
				y3=downCount*30-30;
				y4=downCount*30-30;
			}else if(kaitenCount%4==3) {
				x2=x1;
				x3=x1;
				x4=x1+30;
				y1=downCount*30;
				y2=downCount*30-30;
				y3=downCount*30-60;
				y4=downCount*30-60;
			}
		}
	}
	public void sampleMino(Graphics g) {
		int sx1=0,sx2=0,sx3=0,sx4=0,sy1=0,sy2=0,sy3=0,sy4=0;
		//L字
		if(pattern==0) {
			sx1=430;
			sx2=sx1-30;
			sx3=sx1+30;
			sx4=sx1+30;
			sy1=140;
			sy2=sy1;
			sy3=sy1;
			sy4=sy1-30;
		//凸字
		}else if(pattern==1) {
			sx1=430;
			sx2=sx1-30;
			sx3=sx1+30;
			sx4=sx1;
			sy1=140;
			sy2=sy1;
			sy3=sy1;
			sy4=sy1-30;
		}else if(pattern==2) {
			sx1=430;
			sx2=sx1-30;
			sx3=sx1-30;
			sx4=sx1;
			sy1=140;
			sy2=sy1;
			sy3=sy1-30;
			sy4=sy1-30;
		//S字
		}else if(pattern==3) {
			sx1=430;
			sx2=sx1+30;
			sx3=sx1;
			sx4=sx1-30;
			sy1=140;
			sy2=sy1;
			sy3=sy1-30;
			sy4=sy1-30;
		//逆S字
		}else if(pattern==4) {
			sx1=430;
			sx2=sx1-30;
			sx3=sx1;
			sx4=sx1+30;
			sy1=140;
			sy2=sy1;
			sy3=sy1-30;
			sy4=sy1-30;
		//逆L字
		}else if(pattern==5) {
			sx1=430;
			sx2=sx1-30;
			sx3=sx1-30;
			sx4=sx1+30;
			sy1=140;
			sy2=sy1-30;
			sy3=sy1;
			sy4=sy1;
		//横棒	
		}else if(pattern==6) {
			sx1=430;
			sx2=sx1-30;
			sx3=sx1+30;
			sx4=sx1-60;
			sy1=140;
			sy2=sy1;
			sy3=sy1;
			sy4=sy1;
		}
		b.drawRect(sx1, sy1, 30, 30);
		b.drawRect(sx2, sy2, 30, 30);
		b.drawRect(sx3, sy3, 30, 30);
		b.drawRect(sx4, sy4, 30, 30);
	}
	public void okihantei() {
		try {
			if(downCount>19)downCount=19;
			if(y1==570||y2==570||y3==570||y4==570||banmen[y1/30][x1/30-1]==1||banmen[y2/30][x2/30-1]==1||
					banmen[y3/30][x3/30-1]==1||banmen[y4/30][x4/30-1]==1 ){
				//貫通処理
				if(banmen[y1/30-1][x1/30-1]==1) {
						gameOver=true;
					/*int a=0;
					while(banmen[y1/30-1-a][x1/30-1]==1) {
						a++;
					}
					banmen[y1/30-1-a][x1/30-1]=1;
					banmen[y2/30-1-a][x2/30-1]=1;
					banmen[y3/30-1-a][x3/30-1]=1;
					banmen[y4/30-1-a][x4/30-1]=1;
					downCount=0;
					//x1=150;
					if(colorNum<cols.length-1) {
						colorNum++;
					}else {
						colorNum=0;
					}
					if(pattern==4) {
						pattern=0;
					}else {
						pattern++;
					}
					x1=150;
					kaitenCount=0;*/
				//正常処理
				}else {
					banmen[y1/30-1][x1/30-1]=1;
					banmen[y2/30-1][x2/30-1]=1;
					banmen[y3/30-1][x3/30-1]=1;
					banmen[y4/30-1][x4/30-1]=1;
					downCount=0;
					if(pattern==6) {
						pattern=0;
					}else {
						pattern++;
					}
					//x1=150;
					if(colorNum<cols.length-1) {
						colorNum++;
					}else {
						colorNum=0;
					}
				}
				for(int i=0;i<19;i++) {
					boolean hantei=allEqual(i);
					delete(i,hantei);
				}
				x1=150;
				kaitenCount=0;
			}
		}catch(ArrayIndexOutOfBoundsException e) {
			if(y1>0&&y2>0&&y3>0&&y4>0)gameOver=true;
		}
	}
	public static boolean allEqual(int i) {
			for(int j=0;j<10;j++) {
				if(banmen[i][j]!=1) {
					return false;
				}
			}
			multi++;
			return true;
	}
	public static void delete(int i,boolean hantei) {
		if(hantei==true) {
			try {
				Thread.sleep(200);
			}catch(InterruptedException e) {
			}
			for(int j=0;j<10;j++) {
				banmen[i][j]=0;
			}
			for(int i2=0;i-i2>0;i2++) {
				for(int j=0;j<10;j++) {
					banmen[i-i2][j]=banmen[i-i2-1][j];
				}
			}
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		key=e.getKeyCode();
		if(key==KeyEvent.VK_LEFT) {
			if(x1==30||x2==30||x3==30||x4==30) {
				
			}else {
				x1-=30;
			}
		}else if(key==KeyEvent.VK_RIGHT) {
			if(x1==300||x2==300||x3==300||x4==300) {
				
			}else {
				x1+=30;
			}
		}else if(key==KeyEvent.VK_DOWN) {
			if(downCount>=19||banmen[y1/30][x1/30-1]==1||banmen[y2/30][x2/30-1]==1||
					banmen[y3/30][x3/30-1]==1||banmen[y4/30][x4/30-1]==1 ) {
				
			}else {
				downCount++;
			}
		}else if(key==KeyEvent.VK_UP) {
			kaitenCount++;
		}
		repaint();
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}