package com.development.shmulik.gevoicecontrol;

import android.app.Activity;
import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.development.shmulik.gevoicecontrol.controllers.ModesController;
import com.development.shmulik.gevoicecontrol.exceptions.*;
import com.development.shmulik.gevoicecontrol.models.Commands;

import java.util.ArrayList;
import java.util.Hashtable;

public class GoogleRecognitionActivity extends Activity implements RecognitionListener {

    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;

    private static final String TAG = "shmulik_develop";
    private ModesController modesController;

    public GoogleRecognitionActivity() {
        Commands.init();
        modesController = new ModesController();
    }

    private void addCommandMatches(String cmd, ArrayList<String> matches) {
        if (!Commands.commandsMapping.containsKey(cmd)) {
            Commands.commandsMapping.put(cmd, cmd);
        }
        for (String s : matches) {
            Commands.commandsMapping.put(s, cmd);
        }
    }

    private String getCommand(ArrayList<String> matches) {
        for (String s : matches) {
            String cmd = Commands.commandsMapping.get(s);
            if (cmd != null)
                return cmd;
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_recognition);

        speech = SpeechRecognizer.createSpeechRecognizer(this);
        speech.setRecognitionListener(this);
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,
                "en");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                this.getPackageName());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);
        speech.startListening(recognizerIntent);
    }

    @Override
    public void onReadyForSpeech(Bundle params) {

    }

    @Override
    public void onRmsChanged(float rmsdB) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {
    }

    @Override
    public void onError(int error) {
        speech.startListening(recognizerIntent);
        Log.v(TAG, getErrorText(error));
    }

    @Override
    public void onResults(Bundle results) {
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        for (String m : matches) {
            Log.v(TAG, "Match: " + m);
        }
        String cmd = getCommand(matches);
        if (cmd != null) {
            addCommandMatches(cmd, matches);
            try {
                modesController.parseCommand(cmd);
            } catch (CommandUnknownException e) {
                e.printStackTrace();
            } catch (ModeAlreadyExistException e) {
                e.printStackTrace();
            } catch (ModesCountExceededMaximumException e) {
                e.printStackTrace();
            } catch (ModeDoesntExistException e) {
                e.printStackTrace();
            } catch (com.development.shmulik.gevoicecontrol.exceptions.IllegalStateException e) {
                e.printStackTrace();
            }
        }

        speech.startListening(recognizerIntent);
    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    public static String getErrorText(int errorCode) {
        String message;
        switch (errorCode) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "Audio recording error";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "Client side error";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "Insufficient permissions";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "Network error";
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "Network timeout";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "No match";
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "RecognitionService busy";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "error from server";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "No speech input";
                break;
            default:
                message = "Didn't understand, please try again.";
                break;
        }
        return message;
    }
}
