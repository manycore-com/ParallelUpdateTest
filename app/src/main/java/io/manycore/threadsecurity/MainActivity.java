package io.manycore.threadsecurity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.lang.reflect.Method;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    //public static Semaphore sem0 = new Semaphore(1);
    //public static Semaphore sem1 = new Semaphore(1);
    //public static Semaphore sem2 = new Semaphore(1);
    //public static Semaphore sem3 = new Semaphore(1);

    private static Method sendOnTextChangedMETHOD = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        for (int i = 0; i<10000; i++) {

            MyApplication.executorService.submit(new Runnable() {
                @Override
                public void run() {
                    Log.i("menglog", "act about to update textview");

                    try {
                    } catch (Exception ex) {
                        // android.view.ViewRootImpl$CalledFromWrongThreadException: Only the original thread that created a view hierarchy can touch its views.
                        Log.e("menglog", ex.toString());
                    }
                }
            });
        }
    }


    @SuppressLint("SoonBlockedPrivateApi")
    void testStuff(TextView tv, String text) {
        tv.setText(text);



//        mTextSetFromXmlOrResourceId = false;
//        if (text == null) {
//            text = "";
//        }
//
//        // If suggestions are not enabled, remove the suggestion spans from the text
//        if (!isSuggestionsEnabled()) {
//            text = removeSuggestionSpans(text);
//        }
//
//        if (!mUserSetTextScaleX) mTextPaint.setTextScaleX(1.0f);
//
//        if (text instanceof Spanned
//                && ((Spanned) text).getSpanStart(TextUtils.TruncateAt.MARQUEE) >= 0) {
//            if (ViewConfiguration.get(mContext).isFadingMarqueeEnabled()) {
//                setHorizontalFadingEdgeEnabled(true);
//                mMarqueeFadeMode = MARQUEE_FADE_NORMAL;
//            } else {
//                setHorizontalFadingEdgeEnabled(false);
//                mMarqueeFadeMode = MARQUEE_FADE_SWITCH_SHOW_ELLIPSIS;
//            }
//            setEllipsize(TextUtils.TruncateAt.MARQUEE);
//        }
//
//        int n = mFilters.length;
//        for (int i = 0; i < n; i++) {
//            CharSequence out = mFilters[i].filter(text, 0, text.length(), EMPTY_SPANNED, 0, 0);
//            if (out != null) {
//                text = out;
//            }
//        }
//
//        if (notifyBefore) {
//            if (mText != null) {
//                oldlen = mText.length();
//                sendBeforeTextChanged(mText, 0, oldlen, text.length());
//            } else {
//                sendBeforeTextChanged("", 0, 0, text.length());
//            }
//        }
//
//        boolean needEditableForNotification = false;
//
//        if (mListeners != null && mListeners.size() != 0) {
//            needEditableForNotification = true;
//        }
//
//        PrecomputedText precomputed =
//                (text instanceof PrecomputedText) ? (PrecomputedText) text : null;
//        if (type == TextView.BufferType.EDITABLE || getKeyListener() != null
//                || needEditableForNotification) {
//            createEditorIfNeeded();
//            mEditor.forgetUndoRedo();
//            mEditor.scheduleRestartInputForSetText();
//            Editable t = mEditableFactory.newEditable(text);
//            text = t;
//            setFilters(t, mFilters);
//        } else if (precomputed != null) {
//            if (mTextDir == null) {
//                mTextDir = getTextDirectionHeuristic();
//            }
//            final @PrecomputedText.Params.CheckResultUsableResult int checkResult =
//                    precomputed.getParams().checkResultUsable(getPaint(), mTextDir, mBreakStrategy,
//                            mHyphenationFrequency);
//            switch (checkResult) {
//                case PrecomputedText.Params.UNUSABLE:
//                    throw new IllegalArgumentException(
//                            "PrecomputedText's Parameters don't match the parameters of this TextView."
//                                    + "Consider using setTextMetricsParams(precomputedText.getParams()) "
//                                    + "to override the settings of this TextView: "
//                                    + "PrecomputedText: " + precomputed.getParams()
//                                    + "TextView: " + getTextMetricsParams());
//                case PrecomputedText.Params.NEED_RECOMPUTE:
//                    precomputed = PrecomputedText.create(precomputed, getTextMetricsParams());
//                    break;
//                case PrecomputedText.Params.USABLE:
//                    // pass through
//            }
//        } else if (type == TextView.BufferType.SPANNABLE || mMovement != null) {
//            text = mSpannableFactory.newSpannable(text);
//        } else if (!(text instanceof CharWrapper)) {
//            text = TextUtils.stringOrSpannedString(text);
//        }
//
//        if (mAutoLinkMask != 0) {
//            Spannable s2;
//
//            if (type == TextView.BufferType.EDITABLE || text instanceof Spannable) {
//                s2 = (Spannable) text;
//            } else {
//                s2 = mSpannableFactory.newSpannable(text);
//            }
//
//            if (Linkify.addLinks(s2, mAutoLinkMask)) {
//                text = s2;
//                type = (type == TextView.BufferType.EDITABLE) ? TextView.BufferType.EDITABLE : TextView.BufferType.SPANNABLE;
//
//                /*
//                 * We must go ahead and set the text before changing the
//                 * movement method, because setMovementMethod() may call
//                 * setText() again to try to upgrade the buffer type.
//                 */
//                setTextInternal(text);
//
//                // Do not change the movement method for text that support text selection as it
//                // would prevent an arbitrary cursor displacement.
//                if (mLinksClickable && !textCanBeSelected()) {
//                    setMovementMethod(LinkMovementMethod.getInstance());
//                }
//            }
//        }
//
//        mBufferType = type;
//        setTextInternal(text);
//
//        if (mTransformation == null) {
//            mTransformed = text;
//        } else {
//            mTransformed = mTransformation.getTransformation(text, this);
//        }
//        if (mTransformed == null) {
//            // Should not happen if the transformation method follows the non-null postcondition.
//            mTransformed = "";
//        }
//
//        final int textLength = text.length();
//
//        if (text instanceof Spannable && !mAllowTransformationLengthChange) {
//            Spannable sp = (Spannable) text;
//
//            // Remove any ChangeWatchers that might have come from other TextViews.
//            final ChangeWatcher[] watchers = sp.getSpans(0, sp.length(), ChangeWatcher.class);
//            final int count = watchers.length;
//            for (int i = 0; i < count; i++) {
//                sp.removeSpan(watchers[i]);
//            }
//
//            if (mChangeWatcher == null) mChangeWatcher = new ChangeWatcher();
//
//            sp.setSpan(mChangeWatcher, 0, textLength, Spanned.SPAN_INCLUSIVE_INCLUSIVE
//                    | (CHANGE_WATCHER_PRIORITY << Spanned.SPAN_PRIORITY_SHIFT));
//
//            if (mEditor != null) mEditor.addSpanWatchers(sp);
//
//            if (mTransformation != null) {
//                sp.setSpan(mTransformation, 0, textLength, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//            }
//
//            if (mMovement != null) {
//                mMovement.initialize(this, (Spannable) text);
//
//                /*
//                 * Initializing the movement method will have set the
//                 * selection, so reset mSelectionMoved to keep that from
//                 * interfering with the normal on-focus selection-setting.
//                 */
//                if (mEditor != null) mEditor.mSelectionMoved = false;
//            }
//        }
//
//        if (mLayout != null) {
//            checkForRelayout();
//        }
//
//        sendOnTextChanged(text, 0, oldlen, textLength);
//        onTextChanged(text, 0, oldlen, textLength);
//
//        notifyViewAccessibilityStateChangedIfNeeded(AccessibilityEvent.CONTENT_CHANGE_TYPE_TEXT);
//
//        if (needEditableForNotification) {
//            sendAfterTextChanged((Editable) text);
//        } else {
//            notifyListeningManagersAfterTextChanged();
//        }
//
//        if (mEditor != null) {
//            // SelectionModifierCursorController depends on textCanBeSelected, which depends on text
//            mEditor.prepareCursorControllers();
//
//            mEditor.maybeFireScheduledRestartInputForSetText();
//        }



    }
}