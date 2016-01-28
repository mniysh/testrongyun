package com.example.yanwei.testrongyun.activity;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.yanwei.testrongyun.R;

import java.util.ArrayList;
import java.util.Iterator;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.MessageTag;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.TypingMessage.TypingMessageManager;
import io.rong.imlib.model.Conversation;
import io.rong.message.TextMessage;
import io.rong.message.VoiceMessage;

public class ConversationActivity extends FragmentActivity {
    private final  int SET_TEXT_TYPING_TITLE = 1;
    private final  int SET_VOICE_TYPING_TITLE = 2;
    private final  int SET_TARGETID_TITLE = 3;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private static final String TAG = ConversationActivity.class.getSimpleName();
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //唯一有用的代码，加载一个 layout
        setContentView(R.layout.activity_conversation);
        setListener();
//        enterFragment();
    }

    private void setListener() {
        RongIMClient.setTypingMessageStatusListener(new TypingMessageManager.TypingMessageStatusListener() {
            @Override
            public void onChanged(Conversation.ConversationType conversationType, String s, ArrayList<String> arrayList) {

            }
        });
//        RongIMClient.setTypingStatusListener(new RongIMClient.TypingStatusListener() {
//            @Override
//            public void onTypingStatusChanged(Conversation.ConversationType type, String targetId, Collection<TypingStatus> typingStatusSet) {
//                //当输入状态的会话类型和targetID与当前会话一致时，才需要显示
//                if (type.equals(mConversationType) && targetId.equals(mTargetId)) {
//                    //count表示当前会话中正在输入的用户数量，目前只支持单聊，所以判断大于0就可以给予显示了
//                    int count = typingStatusSet.size();
//                    if (count > 0) {
//                        Iterator iterator = typingStatusSet.iterator();
//                        TypingStatus status = (TypingStatus) iterator.next();
//                        String objectName = status.getTypingContentType();
//
//                        MessageTag textTag = TextMessage.class.getAnnotation(MessageTag.class);
//                        MessageTag voiceTag = VoiceMessage.class.getAnnotation(MessageTag.class);
//                        //匹配对方正在输入的是文本消息还是语音消息
//                        if (objectName.equals(textTag.value())) {
//                            //显示“对方正在输入”
//                            mHandler.sendEmptyMessage(SET_TEXT_TYPING_TITLE);
//                        } else if (objectName.equals(voiceTag.value())) {
//                            //显示"对方正在讲话"
//                            mHandler.sendEmptyMessage(SET_VOICE_TYPING_TITLE);
//                        }
//                    } else {
//                        //当前会话没有用户正在输入，标题栏仍显示原来标题
//                        mHandler.sendEmptyMessage(SET_TARGETID_TITLE);
//                    }
//                }
//            }
//        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
    /**
     * 加载 会话列表 ConversationListFragment
     */
    private void enterFragment() {

        ConversationListFragment fragment = (ConversationListFragment) getSupportFragmentManager().findFragmentById(R.id.rc_frame);

        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//设置群组会话聚合显示
                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")//设置讨论组会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")//设置系统会话非聚合显示
                .build();

        fragment.setUri(uri);
    }
}
