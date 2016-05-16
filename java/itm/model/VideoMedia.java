package itm.model;

/**
 * *****************************************************************************
 * This file is part of the ITM course 2016 (c) University of Vienna 2009-2016
 ******************************************************************************
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

public class VideoMedia extends AbstractMedia {

    // ***************************************************************
    // Fill in your code here!
    // ***************************************************************

    /* video format metadata */
    
    /**
     * Video codec.
     */
    protected String videoCodec;
    
    /**
     * Video codec ID.
     */
    protected String videoCodecID;
    
    /**
     * Video frame rate.
     */
    protected double videoFrameRate;
    
    /**
     * Video length in seconds.
     */
    protected long videoLength;
    
    /**
     * Video width in pixels.
     */
    protected int videoWidth;
    
    /**
     * Video height in pixels.
     */
    protected int videoHeight;
    
    

    /* audio format metadata */
    
    /**
     * Audio codec.
     */
    protected String audioCodec;
    
    /**
     * Audio codec ID.
     */
    protected String audioCodecID;
    
    /**
     * Number of audio channels.
     */
    protected int audioChannels;
    
    /**
     * Audio sample rate in Hz.
     */
    protected double audioSampleRate;
    
    /**
     * Audio bit rate in kb/s.
     */
    protected int audioBitRate;

    /**
     * Get Video codec.
     * @return the videoCodec
     */
    public String getVideoCodec() {
        return videoCodec;
    }

    /**
     * Set Video codec.
     * @param videoCodec the videoCodec to set
     */
    public void setVideoCodec(String videoCodec) {
        this.videoCodec = videoCodec;
    }

    /**
     * Get Video codec ID.
     * @return the videoCodecID
     */
    public String getVideoCodecID() {
        return videoCodecID;
    }

    /**
     * Set Video codec ID.
     * @param videoCodecID the videoCodecID to set
     */
    public void setVideoCodecID(String videoCodecID) {
        this.videoCodecID = videoCodecID;
    }

    /**
     * Get Video frame rate.
     * @return the videoFrameRate
     */
    public double getVideoFrameRate() {
        return videoFrameRate;
    }

    /**
     * Set Video frame rate.
     * @param videoFrameRate the videoFrameRate to set
     */
    public void setVideoFrameRate(double videoFrameRate) {
        this.videoFrameRate = videoFrameRate;
    }

    /**
     * Get Video length in seconds.
     * @return the videoLength
     */
    public long getVideoLength() {
        return videoLength;
    }

    /**
     * Set Video length in seconds.
     * @param videoLength the videoLength to set
     */
    public void setVideoLength(long videoLength) {
        this.videoLength = videoLength;
    }

    /**
     * Get Video width in pixels.
     * @return the videoWidth
     */
    public int getVideoWidth() {
        return videoWidth;
    }

    /**
     * Set Video width in pixels.
     * @param videoWidth the videoWidth to set
     */
    public void setVideoWidth(int videoWidth) {
        this.videoWidth = videoWidth;
    }

    /**
     * Get Video height in pixels.
     * @return the videoHeight
     */
    public int getVideoHeight() {
        return videoHeight;
    }

    /**
     * Set Video height in pixels.
     * @param videoHeight the videoHeight to set
     */
    public void setVideoHeight(int videoHeight) {
        this.videoHeight = videoHeight;
    }

    /**
     * Get Audio codec.
     * @return the audioCodec
     */
    public String getAudioCodec() {
        return audioCodec;
    }

    /**
     * Set Audio codec.
     * @param audioCodec the audioCodec to set
     */
    public void setAudioCodec(String audioCodec) {
        this.audioCodec = audioCodec;
    }

    /**
     * Get Audio codec ID.
     * @return the audioCodecID
     */
    public String getAudioCodecID() {
        return audioCodecID;
    }

    /**
     * Set Audio codec ID.
     * @param audioCodecID the audioCodecID to set
     */
    public void setAudioCodecID(String audioCodecID) {
        this.audioCodecID = audioCodecID;
    }

    /**
     * Get Number of audio channels.
     * @return the audioChannels
     */
    public int getAudioChannels() {
        return audioChannels;
    }

    /**
     * Set Number of audio channels.
     * @param audioChannels the audioChannels to set
     */
    public void setAudioChannels(int audioChannels) {
        this.audioChannels = audioChannels;
    }

    /**
     * Get Audio sample rate in Hz.
     * @return the audioSampleRate
     */
    public double getAudioSampleRate() {
        return audioSampleRate;
    }

    /**
     * Set Audio sample rate in Hz.
     * @param audioSampleRate the audioSampleRate to set
     */
    public void setAudioSampleRate(double audioSampleRate) {
        this.audioSampleRate = audioSampleRate;
    }

    /**
     * Get Audio bit rate in kb/s.
     * @return the audioBitRate
     */
    public int getAudioBitRate() {
        return audioBitRate;
    }

    /**
     * Set Audio bit rate in kb/s.
     * @param audioBitRate the audioBitRate to set
     */
    public void setAudioBitRate(int audioBitRate) {
        this.audioBitRate = audioBitRate;
    }
    
    /**
     * Constructor.
     */
    public VideoMedia() {
        super();
    }

    /**
     * Constructor.
     */
    public VideoMedia(File instance) {
        super(instance);
    }

    /* GET / SET methods */
    // ***************************************************************
    // Fill in your code here!
    // ***************************************************************

    /* (de-)serialization */
    /**
     * Serializes this object to the passed file.
     *
     */
    @Override
    public StringBuffer serializeObject() throws IOException {
        StringWriter data = new StringWriter();
        PrintWriter out = new PrintWriter(data);
        out.println("type: video");
        StringBuffer sup = super.serializeObject();
        out.print(sup);

        /* video fields */
        // ***************************************************************
        // Fill in your code here!
        // ***************************************************************
        out.println( "video_codec: " + getVideoCodec() );
        out.println( "video_codec_ID: " + getVideoCodecID() );
        out.println( "video_frame_rate: " + getVideoFrameRate() );
        out.println( "video_height: " + getVideoHeight() );
        out.println( "video_width: " + getVideoWidth() );
        out.println( "video_length: " + getVideoLength() );
        out.println( "audio_codec: " + getAudioCodec() );
        out.println( "audio_codec_ID: " + getAudioCodecID() );
        out.println( "audio_bit_rate: " + getAudioBitRate() );
        out.println( "audio_channels: " + getAudioChannels() );
        out.println( "audio_sampel_rate: " + getAudioSampleRate() );

        
        return data.getBuffer();
    }

    /**
     * Deserializes this object from the passed string buffer.
     */
    @Override
    public void deserializeObject(String data) throws IOException {
        super.deserializeObject(data);

        StringReader sr = new StringReader(data);
        BufferedReader br = new BufferedReader(sr);
        String line = null;
        while ((line = br.readLine()) != null) {

            /* video fields */
            // ***************************************************************
            // Fill in your code here!
            // ***************************************************************
            
            if ( line.startsWith( "video_codec: " ) ) {
            setVideoCodec( line.substring( "video_codec: ".length() ) );
            } 
            else if ( line.startsWith( "video_codec_id: " ) ) {
            setVideoCodecID( line.substring( "video_codec_id: ".length() ) );
            }
            else if ( line.startsWith( "video_frame_rate: " ) ) {
            setVideoFrameRate( Double.parseDouble( line.substring( "video_frame_rate: ".length() ) ) );
            }
            else if ( line.startsWith( "video_height: " ) ) {
            setVideoHeight( Integer.parseInt( line.substring( "video_height: ".length() ) ) );
            }
            else if ( line.startsWith( "video_width: " ) ) {
            setVideoWidth( Integer.parseInt( line.substring( "video_width: ".length() ) ) );
            }
            else if ( line.startsWith( "video_length: " ) ) {
            setVideoLength( Long.parseLong( line.substring( "video_length: ".length() ) ) );
            }
            else if ( line.startsWith( "audio_codec: " ) ) {
            setAudioCodec( line.substring( "audio_codec: ".length() ) );
            }
            else if ( line.startsWith( "audio_codec_id: " ) ) {
            setAudioCodecID( line.substring( "audio_codec_id: ".length() ) );
            }
            else if ( line.startsWith( "audio_bit_rate: " ) ) {
            setAudioBitRate( Integer.parseInt( line.substring( "audio_bit_rate: ".length() ) ) );
            }
            else if ( line.startsWith( "audio_channels: " ) ) {
            setAudioChannels( Integer.parseInt( line.substring( "audio_channels: ".length() ) ) );
            }
            else if ( line.startsWith( "audio_sample_rate: " ) ) {
            setAudioSampleRate( Double.parseDouble( line.substring( "audio_sample_rate: ".length() ) ) );
            }
        }
    }

}
