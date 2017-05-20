package com.example.android.miwok;

import android.content.Context;

import android.media.*;
import android.media.AudioManager.*;
import android.media.MediaPlayer.*;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.List;

/**
 * Created by Maura on 5/19/2017.
 */

public class OnClickWordListener implements AdapterView.OnItemClickListener {
    
    private final String ACTIVITY_NAME = OnClickWordListener.class.getSimpleName();
    
    private AudioManager audioManager;
    private MediaPlayer mediaPlayer;
    private OnAudioFocusChangeListener audioFocusChangeListener;
    
    private Context context;
    private List<Word> words;
    
    public OnClickWordListener ( Context context, List<Word> words ) {
        this.context = context;
        this.words = words;
    }
    
    @Override
    public void onItemClick ( AdapterView<?> adapterView, View view, int i, long l ) {
        Word word = words.get( i );
        setMediaPlayer( word.getAudioResourceId() );
    }
    
    private void setMediaPlayer ( int audioResourceId ) {
        releaseMediaPlayer();
        setAudioManager();
        
        if ( hasAutoFocus() ) {
            Log.i( ACTIVITY_NAME, "AUDIOFOCUS_REQUEST_GRANTED" );
            playAudio( audioResourceId );
        }
    }
    
    private void setAudioManager () {
        if ( audioManager == null ) {
            audioManager = ( AudioManager ) context.getSystemService( Context.AUDIO_SERVICE );
            setAudioFocusChangeListener();
        }
    }
    
    private boolean hasAutoFocus () {
        return audioManager.requestAudioFocus( audioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT ) == AudioManager.AUDIOFOCUS_REQUEST_GRANTED;
    }
    
    public void playAudio ( int audioResourceId ) {
        mediaPlayer = MediaPlayer.create( context, audioResourceId );
        mediaPlayer.setOnCompletionListener( createOnCompletionListener() );
        mediaPlayer.start();
    }
    
    public void pauseAudio () {
        if ( mediaPlayer != null ) {
            mediaPlayer.pause();
            
            Log.i( ACTIVITY_NAME, "Media Player paused" );
        }
    }
    
    public void releaseMediaPlayer () {
        if ( mediaPlayer != null ) {
            mediaPlayer.release();
            mediaPlayer = null;
            
            Log.i( ACTIVITY_NAME, "Media Player released" );
            
            audioManager.abandonAudioFocus( audioFocusChangeListener );
        }
    }
    
    private OnCompletionListener createOnCompletionListener () {
        return new OnCompletionListener() {
            @Override
            public void onCompletion ( MediaPlayer mediaPlayer ) {
                releaseMediaPlayer();
            }
        };
    }
    
    private void setAudioFocusChangeListener () {
        audioFocusChangeListener = new OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange ( int focusChange ) {
                Log.i( ACTIVITY_NAME, "In onAudioFocusChange method" );
                
                switch ( focusChange ) {
                    case AudioManager.AUDIOFOCUS_REQUEST_GRANTED: {
                        Log.i( ACTIVITY_NAME, "AUDIOFOCUS_REQUEST_GRANTED" );
                        
                        break;
                    }
                    case AudioManager.AUDIOFOCUS_LOSS: {
                        Log.i( ACTIVITY_NAME, "AUDIOFOCUS_LOSS" );
                        releaseMediaPlayer();
                        break;
                    }
                    case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT: {
                        Log.i( ACTIVITY_NAME, "AUDIOFOCUS_LOSS_TRANSIENT" );
                        pauseAudio();
                        break;
                    }
                    case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK: {
                        Log.i( ACTIVITY_NAME, "AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK" );
                        pauseAudio();
                        break;
                    }
                    default: {
                        Log.w( ACTIVITY_NAME, "OTHER: Unrecognized AUDIOFOCUS: " + focusChange );
                    }
                }
            }
        };
    }
}
