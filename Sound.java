import java.applet.*;

public class Sound {
   public static final AudioClip BALL = Applet.newAudioClip(Sound.class.getResource("ball.wav"));
   public static final AudioClip GAMEOVER = Applet.newAudioClip(Sound.class.getResource("win.wav"));
   public static final AudioClip BACK = Applet.newAudioClip(Sound.class.getResource("back.wav"));
}