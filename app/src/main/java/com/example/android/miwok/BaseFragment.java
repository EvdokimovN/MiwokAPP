package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by evdokimovn on 29.06.16.
 */
public class BaseFragment extends Fragment {

    private WordAdapter adapter;


    /** Handles playback of all the sound files */
    private MediaPlayer mMediaPlayer;

    /** Handles audio focus when playing a sound file */
    private AudioManager mAudioManager;

    /** Arrays of words used in apllication */
    private ArrayList<Word> words;

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };


    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };


    public BaseFragment(){



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        words = new ArrayList<Word>();
        Bundle bundle = getArguments();
        Integer category = bundle.getInt(MainActivity.KEY);

        switch (category){
            case R.string.category_family:
                words.add(new Word(R.string.family_father, R.string.miwok_family_father,
                        R.drawable.family_father, R.raw.family_father));
                words.add(new Word(R.string.family_mother, R.string.miwok_family_mother,
                        R.drawable.family_mother, R.raw.family_mother));
                words.add(new Word(R.string.family_son, R.string.miwok_family_son,
                        R.drawable.family_son, R.raw.family_son));
                words.add(new Word(R.string.family_daughter, R.string.miwok_family_daughter,
                        R.drawable.family_daughter, R.raw.family_daughter));
                words.add(new Word(R.string.family_older_brother, R.string.miwok_family_older_brother,
                        R.drawable.family_older_brother, R.raw.family_older_brother));
                words.add(new Word(R.string.family_younger_brother, R.string.miwok_family_younger_brother,
                        R.drawable.family_younger_brother, R.raw.family_younger_brother));
                words.add(new Word(R.string.family_older_sister, R.string.miwok_family_older_sister,
                        R.drawable.family_older_sister, R.raw.family_older_sister));
                words.add(new Word(R.string.family_younger_sister, R.string.miwok_family_younger_sister,
                        R.drawable.family_younger_sister, R.raw.family_younger_sister));
                words.add(new Word(R.string.family_grandmother, R.string.miwok_family_grandmother,
                        R.drawable.family_grandmother, R.raw.family_grandmother));
                words.add(new Word(R.string.family_grandfather, R.string.miwok_family_grandfather,
                        R.drawable.family_grandfather, R.raw.family_grandfather));
                this.adapter = new WordAdapter(getActivity(), words, R.color.category_family);

                break;
            case R.string.category_colors:
                words.add(new Word(R.string.color_red, R.string.miwok_color_red,
                        R.drawable.color_red, R.raw.color_red));
                words.add(new Word(R.string.color_mustard_yellow, R.string.miwok_color_mustard_yellow,
                        R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
                words.add(new Word(R.string.color_dusty_yellow, R.string.miwok_color_dusty_yellow,
                        R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
                words.add(new Word(R.string.color_green, R.string.miwok_color_green,
                        R.drawable.color_green, R.raw.color_green));
                words.add(new Word(R.string.color_brown, R.string.miwok_color_brown,
                        R.drawable.color_brown, R.raw.color_brown));
                words.add(new Word(R.string.color_gray, R.string.miwok_color_gray,
                        R.drawable.color_gray, R.raw.color_gray));
                words.add(new Word(R.string.color_black, R.string.miwok_color_black,
                        R.drawable.color_black, R.raw.color_black));
                words.add(new Word(R.string.color_white, R.string.miwok_color_white,
                        R.drawable.color_white, R.raw.color_white));
                this.adapter = new WordAdapter(getActivity(), words, R.color.category_colors);

                break;
            case R.string.category_numbers:
                words.add(new Word(R.string.number_one, R.string.miwok_number_one,
                        R.drawable.number_one, R.raw.number_one));
                words.add(new Word(R.string.number_two, R.string.miwok_number_two,
                        R.drawable.number_two, R.raw.number_two));
                words.add(new Word(R.string.number_three, R.string.miwok_number_three,
                        R.drawable.number_three, R.raw.number_three));
                words.add(new Word(R.string.number_four, R.string.miwok_number_four,
                        R.drawable.number_four, R.raw.number_four));
                words.add(new Word(R.string.number_five, R.string.miwok_number_five,
                        R.drawable.number_five, R.raw.number_five));
                words.add(new Word(R.string.number_six, R.string.miwok_number_six,
                        R.drawable.number_six, R.raw.number_six));
                words.add(new Word(R.string.number_seven, R.string.miwok_number_seven,
                        R.drawable.number_seven, R.raw.number_seven));
                words.add(new Word(R.string.number_eight, R.string.miwok_number_eight,
                        R.drawable.number_eight, R.raw.number_eight));
                words.add(new Word(R.string.number_nine, R.string.miwok_number_nine,
                        R.drawable.number_nine, R.raw.number_nine));
                words.add(new Word(R.string.number_ten, R.string.miwok_number_ten,
                        R.drawable.number_ten, R.raw.number_ten));
                this.adapter = new WordAdapter(getActivity(), words, R.color.category_numbers);

                break;
            case R.string.category_phrases:
                words.add(new Word(R.string.phrase_where_are_you_going,
                        R.string.miwok_phrase_where_are_you_going, R.raw.phrase_where_are_you_going));
                words.add(new Word(R.string.phrase_what_is_your_name,
                        R.string.miwok_phrase_what_is_your_name, R.raw.phrase_what_is_your_name));
                words.add(new Word(R.string.phrase_my_name_is,
                        R.string.miwok_phrase_my_name_is, R.raw.phrase_my_name_is));
                words.add(new Word(R.string.phrase_how_are_you_feeling,
                        R.string.miwok_phrase_how_are_you_feeling, R.raw.phrase_how_are_you_feeling));
                words.add(new Word(R.string.phrase_im_feeling_good,
                        R.string.miwok_phrase_im_feeling_good, R.raw.phrase_im_feeling_good));
                words.add(new Word(R.string.phrase_are_you_coming,
                        R.string.miwok_phrase_are_you_coming, R.raw.phrase_are_you_coming));
                words.add(new Word(R.string.phrase_yes_im_coming,
                        R.string.miwok_phrase_yes_im_coming, R.raw.phrase_yes_im_coming));
                words.add(new Word(R.string.phrase_im_coming,
                        R.string.miwok_phrase_im_coming, R.raw.phrase_im_coming));
                words.add(new Word(R.string.phrase_lets_go,
                        R.string.miwok_phrase_lets_go, R.raw.phrase_lets_go));
                words.add(new Word(R.string.phrase_come_here,
                        R.string.miwok_phrase_come_here, R.raw.phrase_come_here));
                // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
                // adapter knows how to create list items for each item in the list.
                this.adapter = new WordAdapter(getActivity(), words, R.color.category_phrases);
                break;
        }
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);



        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);

        // Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                // Get the {@link Word} object at the given position the user clicked on
                Word word = (Word) adapterView.getItemAtPosition(i);

                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now.

                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    mMediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResourceId());

                    // Start the audio file
                    mMediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });

        return rootView;
    }


    @Override
    public void onStart() {
        super.onStart();
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
