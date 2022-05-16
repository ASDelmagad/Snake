package snake;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundPlayer
{
    private FloatControl backgroundGainControl; // Constantly looping background song, so saving its control
    private Float effectsGainLevel = (float)20.0 * (float)Math.log10(0.8); // Always new soundeffect control, so saving the value

    /**
     * Plays background music, only to be called once at the start of the game.
     * @param musicLocation
     */
    public void playMusic(String fileName)
    {
        if(backgroundGainControl != null)
            return;

        try
        {
            URL musicLocation = getClass().getResource(fileName);

            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicLocation);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);

            this.backgroundGainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            this.setBackgroundVolume((float)0.3);

            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void playSound(String fileName)
    {
        try
        {
            URL sfxLocation = getClass().getResource(fileName);

            AudioInputStream audioInput = AudioSystem.getAudioInputStream(sfxLocation);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);

            FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(effectsGainLevel);

            clip.start();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    // - - - - - [Get/Set methods]

    /**
     * Returns the background volume in linear scaling. 0.0 to 1.0
     * @return backgroundVolume
     */
    public float getBackgroundVolume()
    {
        return (float)Math.pow(10.0, this.backgroundGainControl.getValue() / 20.0); // Some math from the internet on decibel to linear conversion
    }

    /**
     * Returns the effectsGainLevel variable. Scale in 0.0 to 1.0 in linear scaling
     * @return effectsGainLevel
     */
    public float getEffectsVolume()
    {
        return (float)Math.pow(10.0, this.effectsGainLevel / 20.0);
    }

    //-----------------------------------------------------------------
    
    /**
     * Sets the background volume in linear scaling. 0.0 to 1.0
     * If volume is not in that scale, the function returns.
     * @param volume
     */
    public void setBackgroundVolume(float volume)
    {
        if(volume <= (float)0.01)
        {
            this.backgroundGainControl.setValue((float)0.0);
            return;
        }
        
        if(volume > (float)1.0)
            volume = (float)1.0;
        
        this.backgroundGainControl.setValue((float)20.0 * (float)Math.log10(volume)); // Some math from the internet on decibel to linear conversion
    }

    /**
     * Sets the effectsGainLevel variable value. Scaling is 0.0 to 1.0 in linear.
     * @param volume
     */
    public void setEffectsVolume(float volume)
    {
        if(volume <= (float)0.0)
        {
            effectsGainLevel = (float)0.0;
            return;
        }
        
        if(volume > (float)1.0)
            volume = (float)1.0;
        
        effectsGainLevel = (float)20.0 * (float)Math.log10(volume); // Some math from the internet on decibel to linear conversion
    }
}
