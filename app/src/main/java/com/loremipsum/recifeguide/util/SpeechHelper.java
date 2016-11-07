package com.loremipsum.recifeguide.util;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

/**
 * Created by Christian.Palmer on 07/11/2016.
 */

public class SpeechHelper {

    /*
    Como utilizar: instanciar a classe passando o contexto da activity no construtor
    Caso houver a necessidade de falar em outro idioma, utilizar o método setLanguage passando
    o string do locale como argumento. Ex: "en-US

    O método .speak faz a mágica, basta passar o texto a ser falado

    no destroy da atividade, chamar o shutdown() (esse método não deve ser chamado duas vezes, por razões óbvias..)

     */

    private TextToSpeech ttsObj = null;
    private Context mContext;
    private TextToSpeech.OnInitListener mInitListener;

    // linguagem padrão
    public String mDefaultLanguage = "pt-BR";
    public String languageEN = "en-US";

    public SpeechHelper(Context context) {

        mInitListener = new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

            }
        };
        ttsObj = new TextToSpeech(context, mInitListener);

        Locale ptBR = new Locale(mDefaultLanguage);
        ttsObj.setLanguage(ptBR);

    }

    public void setLanguage(String lang) {
        Locale locale = new Locale(lang);
        ttsObj.setLanguage(locale);
    }

    public void speak(String text) {

        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            ttsObj.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        } else {
            ttsObj.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }

    }


    public void shutdown() {

        ttsObj.shutdown();
    }

    public void stop() {

        if (ttsObj.isSpeaking()) {
            ttsObj.stop();
        }

    }
}

