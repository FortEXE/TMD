
Almost All the Book's Code

From:
  Killer Game Programming in Java
  Andrew Davison
  O'Reilly, May 2005
  ISBN: 0-596-00730-2
  http://www.oreilly.com/catalog/killergame/
  Web Site for the book: http://fivedots.coe.psu.ac.th/~ad/jg

Contact Address:
  Dr. Andrew Davison
  Dept. of Computer Engineering
  Prince of Songkla University
  Hat Yai, Songkhla 90112, Thailand
  E-mail: ad@fivedots.coe.psu.ac.th


If you use this code, please mention my name, and include a link
to the book's Web site.

Thanks,
  Andrew

-----------------------------------------

This directory is available as a single zip file at:
http://fivedots.coe.psu.ac.th/~ad/jg/code/kgpjCode.zip

This directory contains zip files for each chapter's code.

The table below shows which zip files match which chapters,
and the code directories inside the zip files.


Chapter Name				Code Directories	Zipped File
------------				----------------	-----------
Preface					--

Ch 1 Why Java for Games Programming?	--

Ch 2 An Animation Framework		Timings/		ch2Code.zip

Ch 3 Worms in Windows and Applets	Worm/WormP/
					Worm/WormApplet/
								chs3and4Code.zip
Ch 4 Full-Screen Worms			Worm/WormAFS/
					Worm/WormUFS/
					Worm/WormFSEM/

Ch 5 An Introduction to Java Imaging	--

Ch 6 Image Loading, Visual Effects, and Animation		ch6Code.zip
					ImagesTests/

Ch 7 Introducing Java Sound		SoundExamps/McDonalds/
					SoundExamps/SoundPlayer/

Ch 8 Loading and Playing Sounds		SoundExamps/LoadersTests/   chs7to10Code.zip

Ch 9 Audio Effects			SoundExamps/SoundPlayer/

Ch 10 Audio Synthesis			SoundExamps/SynthSound/

Ch 11 Sprites				BugRunner/		ch11Code.zip

Ch 12 A Side-Scroller			JumpingJack/		ch12Code.zip

Ch 13 An Isometric Tile Game		AlienTiles/		ch13Code.zip

Ch 14 Introducing Java 3D		--

Ch 15 A 3D Checkerboard: Checkers3D	Checkers3D/		ch15Code.zip

Ch 16 Loading and Manipulating External Models			ch16Code.zip
					LoaderInfo3D/
					Loader3D/

Ch 17 Using a Lathe to Make Shapes	Lathe3D/		ch17Code.zip

Ch 18 3D Sprites			Tour3D/			ch18Code.zip

Ch 19 Animated 3D Sprites		AnimTour3D/		ch19Code.zip

Ch 20 An Articulated, Moveable Figure	Mover3D/		ch20Code.zip

Ch 21 Particle Systems			Particles3D/		ch21Code.zip

Ch 22 Flocking Boids			Flocking3D/		ch22Code.zip

Ch 23 Shooting a Gun			Shooter3D/		ch23Code.zip

Ch 24 A First-Person Shooter		FPShooter3D/		ch24Code.zip

Ch 25 A 3D Maze				Maze3D/			ch25Code.zip

Ch 26 Fractal Land			FractalLand3D/		ch26Code.zip

Ch 27 Terrain Generation with Terragen	Terra3D/		ch27Code.zip

Ch 28 Trees That Grow			Trees3D/		ch28Code.zip

Ch 29 Networking Basics			NetBasics/		ch29Code.zip
					NetBasics/Sequential/
					NetBasics/Threaded/
					NetBasics/NIO/
					NetBasics/udp/
					NetBasics/Multicast/
					NetBasics/Firewall/

Ch 30 Network Chat			Chat/			ch30Code.zip
					Chat/Threaded/
					Chat/Multicasting/
					Chat/ChatServlet/

Ch 31 A Networked Two-Person Game	FourByFour/		ch31Code.zip
					NetFourByFour/

Ch 32 A Networked Virtual Environment	NetTour3D/		ch32Code.zip

App. A Installation Using install4j	BugRunner/		appACode.zip
					Checkers3D/

App. B Installation Using Java Web Start			appBCode.zip
					BugRunner/
					Checkers3D/

-------------------------------
Notes

The preface, and chapters 1, 5, and 14 don't have any zipped code.

The code for chapters 3 and 4 are zipped together in chs3and4Code.zip
since they're variants of the same 'worm' example.

The code for chapters 7, 8, 9, and 10 are zipped together in
chs7to10Code.zip since they're all about sound.


---------------------------------
Code that Isn't Here

This directory can be found as a single zip file at:
http://fivedots.coe.psu.ac.th/~ad/jg/code/

Also on that page are four files **NOT** in this directory:

1. NCSA Portfolio (JAR file) (458 KB).
   Used in chapters 16, 18, 19, 24, 27, and 32.
   Portfolio is a very useful collection of model loaders.
   Available as http://fivedots.coe.psu.ac.th/~ad/jg/code/portfolio.jar

2. Daniel Selman's Java3dTree package (JAR file) (73 KB).
   This package is part of the source code downloadable from 
   http://www.manning.com/selman/ for Selman's excellent Java 3D 
   Programming textbook.
   Used in chapter 15.
   Available as http://fivedots.coe.psu.ac.th/~ad/jg/code/j3dtree.jar

3. Two zipped OBJ files used for the landscapes in chapter 27
       test1.obj (1.05MB), test2.obj (1.05MB). 
   Available as http://fivedots.coe.psu.ac.th/~ad/jg/code/test1obj.zip
            and http://fivedots.coe.psu.ac.th/~ad/jg/code/test2obj.zip

---------------------------------
Getting this Code to Work (Part 1)

I sometimes get queries about this code "not compiling" or "not working".
It does compile and work, _but_ you need to have installed Java and
Java 3D first. 

Java 3D is not part of the standard Java distribution; it's a separate
download. Install Java first, then Java 3D.

1. Java
Install either
   J2SE 5.0	http://java.sun.com/j2se/1.5.0/download.jsp
or J2SE 1.4.2   http://java.sun.com/j2se/1.4.2/download.html

The examples work with both versions.


2. Java 3D
Java 3D is used extensively in this book -- it's the basis of all the 
3D examples (15 chapters worth), two of the networking chapters, and 
it's accurate timer class is used in six of the 2D chapters 
(chapters 2, 3, 4, 11, 12, and 13). There are alternatives to using the 
Java 3D timer, which I discuss in chapter 2.

Install either
   Java 3D 1.3.1   http://java.sun.com/products/java-media/3D/download.html
   (go for the OpenGL version if you're not sure)

or Java 3D 1.3.2  https://java3d.dev.java.net/
   (go for the latest release).

The examples work with both 1.3.1 and 1.3.2.

There is a Java 3D 1.4, but that's in development as I write this.

Details on how to test Java 3D _before_ you try out my code can be found 
in chapter 14. 

---------------------------------
Getting this Code to Work (Part 2)

Each of the code directories contains its own readme.txt file which
details how to compile and execute that chapter's examples.

---------
Last updated: 22nd April 2005
