package com.example.yanwei.testrongyun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.yanwei.testrongyun.activity.ConversationActivity;
import com.example.yanwei.testrongyun.activity.ConversationListActivity;
import com.example.yanwei.testrongyun.activity.SubConversationListActivtiy;
import com.example.yanwei.testrongyun.bean.User;
import com.example.yanwei.testrongyun.exception.ResponseException;
import com.example.yanwei.testrongyun.service.DataAccessUtil;
import com.example.yanwei.testrongyun.service.DataParseUtil;
import com.example.yanwei.testrongyun.utils.L;
import com.example.yanwei.testrongyun.utils.T;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.PublicServiceProfileList;

public class MainActivity extends AppCompatActivity {
    private User u;
    private Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = (Button) findViewById(R.id.button);
        Button  b2= (Button) findViewById(R.id.but_list);
        Button  b3= (Button) findViewById(R.id.but_list_juhe);
        Button  b4= (Button) findViewById(R.id.but_service);
        Button  b5= (Button) findViewById(R.id.but_app_public);
        Button  b6= (Button) findViewById(R.id.but_public);
        Button  b7= (Button) findViewById(R.id.but_public_message);
        Button  b8= (Button) findViewById(R.id.but_app_public_message);
        Button  b9= (Button) findViewById(R.id.but_app_search_public);

        lianjie();
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(RongIM.getInstance() != null){
                    T.show("暂无");
//                    /**
//                     * 搜索全部公众服务。
//                     *
//                     * @param searchType       搜索类型枚举。
//                     * @param keywords         搜索关键字。
//                     */
//                    RongIM.getInstance().getRongIMClient().searchPublicService(RongIMClient.SearchType.EXACT, "", new RongIMClient.SearchPublicServiceCallback() {
//                        @Override
//                        public void onError(RongIMClient.ErrorCode e) {
//                            //错误回调处理
//                        }
//                        @Override
//                        public void onSuccess(PublicServiceProfileList publicServiceProfileList) {
//                            //成功回调处理
//                        }
//                    });
                }
            }
        });
        ////应该是有开通之后才能用，
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(RongIM.getInstance() != null){
                    /**
                     * 启动应用公众服务信息界面。
                     *
                     * @param context          应用上下文。
                     * @param conversationType 会话类型。
                     * @param targetId         目标 Id。
                     */
                    RongIM.getInstance().startPublicServiceProfile(MainActivity.this, Conversation.ConversationType.APP_PUBLIC_SERVICE, "9527");
                }

            }
        });
        //应该是有开通之后才能用，
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(RongIM.getInstance()!= null){


                    /**
                     * 启动公共服务号信息界面。
                     *
                     * @param context          应用上下文。
                     * @param conversationType 会话类型。
                     * @param targetId         目标 Id。
                     */
                    RongIM.getInstance().startPublicServiceProfile(MainActivity.this, Conversation.ConversationType.PUBLIC_SERVICE, "9527");
                }
            }
        });
        //公众服务，需要在融云后台开启才可用
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(RongIM.getInstance() != null){
                    /**
                     * 启动公众服务号会话界面。
                     *
                     * @param context          应用上下文。
                     * @param conversationType 开启会话类型。
                     * @param targetId         目标 Id。
                     * @param title            聊天的标题。
                     */
                    RongIM.getInstance().startConversation(MainActivity.this, Conversation.ConversationType.PUBLIC_SERVICE, "9527", "公众帐号标题");
                }
            }
        });
        //应用公众服务，需要在融云后台开启才可用
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(RongIM.getInstance() != null)
                /**
                 * 启动应用公众服务会话界面。
                 *
                 * @param context          应用上下文。
                 * @param conversationType 开启会话类型。
                 * @param targetId         目标 Id。
                 * @param title            聊天的标题。
                 */
                    RongIM.getInstance().startConversation(MainActivity.this, Conversation.ConversationType.APP_PUBLIC_SERVICE, "9527", "公众帐号标题");
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(RongIM.getInstance() != null){
                    RongIM.getInstance().startConversation(MainActivity.this,
                            Conversation.ConversationType.APP_PUBLIC_SERVICE, "KEFU145395261596403","测试客服");
                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(RongIM.getInstance() != null){
                    RongIM.getInstance().startSubConversationList(MainActivity.this, Conversation.ConversationType.GROUP);
                }
//                startActivity(new Intent(MainActivity.this, SubConversationListActivtiy.class));
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(RongIM.getInstance() != null){
                    RongIM.getInstance().startConversationList(MainActivity.this);
                }
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RongIM.getInstance() != null) {
                    RongIM.getInstance().startPrivateChat(MainActivity.this, "567", "hahaha");
                }
            }
        });
    }

    private void lianjie() {
        String token = "9+Q+8WnUHnYCDRaXltsYHndFDHgW5iXsUdjNxw1GQxaU78H5SgkD45WyD0G1HIwqLteRtnVfAw3vXwRh4+IbJw==";
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {

            }
            @Override
            public void onSuccess(String s) {
                Log.e("MainActivity", "——onSuccess—-" + s);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                L.d("onError");
            }
        });
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        /**
//         * 启动单聊
//         * context - 应用上下文。
//         * targetUserId - 要与之聊天的用户 Id。
//         * title - 聊天的标题，如果传入空值，则默认显示与之聊天的用户名称。
//         */
//        if (RongIM.getInstance() != null) {
//            RongIM.getInstance().startPrivateChat(MainActivity.this,"123","title");
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
