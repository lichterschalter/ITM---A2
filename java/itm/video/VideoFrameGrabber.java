package itm.video;

/*******************************************************************************
 This file is part of the ITM course 2016
 (c) University of Vienna 2009-2016
 *******************************************************************************/

import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.MediaListenerAdapter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.mediatool.event.IVideoPictureEvent;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IStream;
import com.xuggle.xuggler.IStreamCoder;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * 
 * This class creates JPEG thumbnails from from video frames grabbed from the
 * middle of a video stream It can be called with 2 parameters, an input
 * filename/directory and an output directory.
 * 
 * If the input file or the output directory do not exist, an exception is
 * thrown.
 */

public class VideoFrameGrabber {

	/**
	 * Constructor.
	 */
	public VideoFrameGrabber() {
	}

	/**
	 * Processes the passed input video file / video file directory and stores
	 * the processed files in the output directory.
	 * 
	 * @param input
	 *            a reference to the input video file / input directory
	 * @param output
	 *            a reference to the output directory
	 */
	public ArrayList<File> batchProcessVideoFiles(File input, File output) throws IOException {
		if (!input.exists())
			throw new IOException("Input file " + input + " was not found!");
		if (!output.exists())
			throw new IOException("Output directory " + output + " not found!");
		if (!output.isDirectory())
			throw new IOException(output + " is not a directory!");

		ArrayList<File> ret = new ArrayList<File>();

		if (input.isDirectory()) {
			File[] files = input.listFiles();
			for (File f : files) {
				if (f.isDirectory())
					continue;

				String ext = f.getName().substring(f.getName().lastIndexOf(".") + 1).toLowerCase();
				if (ext.equals("avi") || ext.equals("swf") || ext.equals("asf") || ext.equals("flv")
						|| ext.equals("mp4")) {
					File result = processVideo(f, output);
					System.out.println("converted " + f + " to " + result);
					ret.add(result);
				}

			}

		} else {
			String ext = input.getName().substring(input.getName().lastIndexOf(".") + 1).toLowerCase();
			if (ext.equals("avi") || ext.equals("swf") || ext.equals("asf") || ext.equals("flv") || ext.equals("mp4")) {
				File result = processVideo(input, output);
				System.out.println("converted " + input + " to " + result);
				ret.add(result);
			}
		}
		return ret;
	}

	/**
	 * Processes the passed audio file and stores the processed file to the
	 * output directory.
	 * 
	 * @param input
	 *            a reference to the input audio File
	 * @param output
	 *            a reference to the output directory
	 */
	protected File processVideo(File input, File output) throws IOException, IllegalArgumentException {
		if (!input.exists())
			throw new IOException("Input file " + input + " was not found!");
		if (input.isDirectory())
			throw new IOException("Input file " + input + " is a directory!");
		if (!output.exists())
			throw new IOException("Output directory " + output + " not found!");
		if (!output.isDirectory())
			throw new IOException(output + " is not a directory!");

		File outputFile = new File(output, input.getName() + "_thumb.jpg");
		// load the input video file

		// ***************************************************************
		// Fill in your code here!
		// ***************************************************************
                IMediaReader mReader = ToolFactory.makeReader( input.toString() );
                mReader.setBufferedImageTypeToGenerate(BufferedImage.TYPE_3BYTE_BGR);
                mReader.addListener(new VideoFrameListener( input, outputFile ));
                mReader.readPacket();
                try{
                        while (mReader.readPacket() == null) ;
                }catch( Exception ex ){
                        System.out.println( "Problem with reading the packets of " + input.toString() + ": " + ex.getMessage() );
                }
                
                            
		return outputFile;

	}
        
        /**
         * This class grabs a BufferedImage of the middle of the video and saves is as JPEG.
         */
        private static class VideoFrameListener extends MediaListenerAdapter {
                private File input, output;
                
                /**
                 * Init the class.
                 * @param input file or directory
                 * @param output file
                 */
                public VideoFrameListener( File input, File output ){
                    this.input = input;
                    this.output = output;
                }
            
                /**
                 * Grab a frame if the timestamp is the half of the duration of the video.
                 * @param event from media.readPacket
                 */
                public void onVideoPicture(IVideoPictureEvent event) {
                       /* double duration = 1;
                        double fps = 12;
                        IContainer cont = IContainer.make();
                        if( cont.open(input.toString(), IContainer.Type.READ, null) > 0 )
                            duration = cont.getDuration() / 1000 / 1000 ; 
                        else throw new RuntimeException("Problems with opening the media file!");
                        for( int i = 0; i < cont.getNumStreams(); ++i ){
                            IStream stream = cont.getStream(i);
                            IStreamCoder streamCoder = stream.getStreamCoder();
                            if (streamCoder.getCodecType() == ICodec.Type.CODEC_TYPE_VIDEO) {
                                fps = streamCoder.getFrameRate().getDouble();
                            }
                        }                       
                        */
                        if( event.getTimeStamp() >= 4.0 / 2 * 24.0 ){
                            try {                            
                                this.saveImage( event.getImage() );
                            } catch (IOException ex) {
                                System.out.println( "Saving the frame did´nt work. " + ex.getMessage() );
                            }
                        }
                }
                
                /**
                 * Save a BufferedImage to a file.
                 * @param buf BufferedImage to save
                 * @throws IOException 
                 */
                public void saveImage( BufferedImage buf ) throws IOException{
                        ImageIO.write( buf, "JPEG", output ); 
                }
        }

	/**
	 * Main method. Parses the commandline parameters and prints usage
	 * information if required.
	 */
	public static void main(String[] args) throws Exception {

		// args = new String[] { "./media/video", "./test" };

		if (args.length < 2) {
			System.out.println("usage: java itm.video.VideoFrameGrabber <input-videoFile> <output-directory>");
			System.out.println("usage: java itm.video.VideoFrameGrabber <input-directory> <output-directory>");
			System.exit(1);
		}
		File fi = new File(args[0]);
		File fo = new File(args[1]);
		VideoFrameGrabber grabber = new VideoFrameGrabber();
		grabber.batchProcessVideoFiles(fi, fo);
	}

}
