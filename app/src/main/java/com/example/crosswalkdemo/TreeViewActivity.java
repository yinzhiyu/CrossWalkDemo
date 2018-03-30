package com.example.crosswalkdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class TreeViewActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MyAdapter mMyAdapter;
    private TreeBean treeBean;
    private List<TreeBean.DataBean.NodeListBean.InfoBean> treeBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_tree_view);
        super.onCreate(savedInstanceState);
        initView();
        getData();
    }

    private void getData() {
        String tree = getJson("tree.json", this);
        System.out.println("xxxxxxx:" + tree);
        treeBean = new Gson().fromJson(tree, TreeBean.class);
        treeBeanList = treeBean.getData().getNode_list().getInfo();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mMyAdapter = new MyAdapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mMyAdapter);
    }

    public static void start(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, TreeViewActivity.class);
        activity.startActivity(intent);
    }

    public static String getJson(String fileName, Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream is = context.getAssets().open(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_multilayer, parent, false);
            ViewHolder holder = new ViewHolder(itemView);
            return holder;
        }

        @Override
        public int getItemCount() {
            return treeBean.getData().getNode_list().getInfo().size();
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            int childNum = treeBeanList.get(position).getChildren().size();
            if (childNum > 0) {//判断是否有叶节点
                holder.open_status_image.setImageResource(R.mipmap.list_close);
                holder.arrow.setVisibility(View.GONE);
            } else {
                holder.open_status_image.setImageResource(R.mipmap.list_last_level);
                holder.arrow.setVisibility(View.VISIBLE);
            }
            holder.level_name.setText(treeBeanList.get(position).getTf_catelog());//节点名称
            final int treePosition = position;
            holder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    TreeBean.DataBean.NodeListBean.InfoBean newslistBean=new TreeBean.DataBean.NodeListBean.InfoBean();
                    newslistBean.setTf_id(treeBeanList.get(position).getChildren().get(0).getTf_id());
                    newslistBean.setTf_catelog(treeBeanList.get(position).getChildren().get(0).getTf_catelog());
                    treeBeanList.add(newslistBean);
                }
            });
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private RelativeLayout root;
            private ImageView open_status_image;
            private TextView level_name;
            private TextView level_status;
            private TextView no_read_num;
            private ImageView arrow;
            private LinearLayout status_layout;

            public ViewHolder(View view) {
                super(view);

                root = (RelativeLayout) view.findViewById(R.id.root);
                open_status_image = (ImageView) view.findViewById(R.id.open_status_image);
//                app.setMViewMargin(open_status_image,0f,0.048f,0.03f,0f);
                status_layout = (LinearLayout) view.findViewById(R.id.status_layout);
//                app.setMViewMargin(status_layout,0f,0.035f,0.11f,0.035f);
                level_name = (TextView) view.findViewById(R.id.level_name);
//                app.setMTextSize(level_name,13);
                level_status = (TextView) view.findViewById(R.id.level_status);
//                app.setMTextSize(level_status,12);
                no_read_num = (TextView) view.findViewById(R.id.no_read_num);
//                app.setMTextSize(no_read_num,12);
                arrow = (ImageView) view.findViewById(R.id.arrow);
//                app.setMLayoutParam(arrow,0.04f,0.04f);
//                app.setMViewMargin(arrow,0f,0f,0.032f,0f);

//                app.setMViewPadding(no_read_num, 0.015f, 0, 0.015f, 0.003f);

//                view.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        int position = getLayoutPosition();
//                        TreeBean.DataBean.NodeListBean.InfoBean tree = treeBean.getData().getNode_list().getInfo().get(position);
//                        int childNum = tree.getChildren().size();
//                        if (childNum > 0) {
//
//                        }
//                        if (!TextUtils.equals(info.getType(), "2")) {
//                            //不是最后一层
//                            ArrayList<TreeBean.DataBean.NodeListBean.InfoBean> mDelArrayList = new ArrayList<>();//要删除的数据集合
//                            //只关闭子集
//                            for (int i = position + 1; i < showList.size(); i++) {
//                                TreeBean.DataBean.NodeListBean.InfoBean rbean = showList.get(i);
//                                TreeBean.DataBean.NodeListBean.InfoBean.InfoEntity rInfo = rbean.getInfo();
//                                if (Integer.parseInt(rInfo.get_deepth()) > Integer.parseInt(info.get_deepth())) {
//                                    mDelArrayList.add(rbean);
//                                    rInfo.setOpen(false);
//                                } else {
//                                    break;
//                                }
//                            }

//                            showList.removeAll(mDelArrayList);//删除需要删除的数据
//                            mMyAdapter.notifyDataSetChanged();
//                            mDelArrayList.clear();
//
//                            if (info.isOpen()) {
//                                //点击的层级是已打开状态，设置关闭状态
//                                info.setOpen(false);
//                            } else {
//                                //点击的层级是关闭状态，设置打开状态
//                                info.setOpen(true);
//                                //将需要添加的数据添加到点击的层级下面
//                                List<TreeBean.DataBean.NodeListBean.InfoBean> list = bean.getChildren();
//                                int index = position;
//                                for (TreeBean.DataBean.NodeListBean.InfoBean addBean : list) {
//                                    if (clearMap.get(addBean.getInfo().getId()) == null) {
//                                        showList.add(++index, addBean);
//                                    }
//                                }
//                            }
//                            mMyAdapter.notifyDataSetChanged();
//                        } else {
//                            //最后一层，跳转
////                            Intent intent = new Intent(getActivity(), TaskDetailActivity.class);
//                            Intent intent = new Intent(getActivity(), TaskDetailELVActivity.class);
//                            intent.putExtra("title", info.getName());
//                            intent.putExtra("node_id", info.getId());
//                            String unread = info.getNode_unread();
//                            if (!TextUtils.equals(unread, "")) {
//                                TreeBean.DataBean.NodeListBean.InfoBean psBean = bean;
//                                info.setNode_unread("");
//
//                                //循环的flag，循环查找上级id，直到parentid匹配不到为止（匹配不到是为根目录）
//                                boolean flag = true;
//                                while (flag) {
//                                    String parentid = null;
//                                    for (int i = 0; i < position; i++) {
//                                        TreeBean.DataBean.NodeListBean.InfoBean TreeBean.DataBean.NodeListBean.InfoBean = showList.get(i);
//                                        if (TextUtils.equals(TreeBean.DataBean.NodeListBean.InfoBean.getInfo().getId(), psBean.getInfo().getParentid())) {
//                                            psBean = TreeBean.DataBean.NodeListBean.InfoBean;
//                                            int unr = Integer.parseInt(psBean.getInfo().getUnread()) - Integer.parseInt(unread);
//                                            psBean.getInfo().setUnread(unr + "");
//                                            parentid = psBean.getInfo().getParentid();
//                                            break;
//                                        }
//                                    }
//
//                                    if (null == parentid) {
//                                        flag = false;
//                                    }
//                                }
//                                mMyAdapter.notifyDataSetChanged();
//                            }
//
//                            getActivity().startActivity(intent);
//                        }
//                    }
//                });
//
//                view.setTag(this);
            }
        }
    }
}
