package com.miniproject.a1st.a1stminiproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.miniproject.a1st.a1stminiproject.data.BoardPost;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 글 목록 화면
 * A simple {@link Fragment} subclass.
 */
public class BoardFragment extends Fragment {

    @BindView(R.id.board_list)
    RecyclerView mBoardList;

    private BoardListAdapter mBoardListAdapter;
    private ArrayList<BoardPost> DUMMY_BOARDS;

    public BoardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board, container, false);
        ButterKnife.bind(this, view);

        // 글 추가 (DUMMY_DATA)
        DUMMY_BOARDS = new ArrayList<>();
        DUMMY_BOARDS.add(new BoardPost("<b><font color=black>비디오머그1 - VIDEO MUG</font></b>님이 방송을 종료했습니다.", "2시간",
                "[비디오머그 라이브1] 최순실 딸 정유라 강제송환\n\n☞ 고화질 감상 https://youtu.be/XjLDue4kpRk", null));
        DUMMY_BOARDS.add(new BoardPost("<b><font color=black>비디오머그2 - VIDEO MUG</font></b>님이 방송을 종료했습니다.", "3시간",
                "[비디오머그 라이브2] 최순실 딸 정유라 강제송환\n\n☞ 고화질 감상 https://youtu.be/XjLDue4kpRk", null));
        DUMMY_BOARDS.add(new BoardPost("<b><font color=black>비디오머그3 - VIDEO MUG</font></b>님이 방송을 종료했습니다.", "4시간",
                "[비디오머그 라이브3] 최순실 딸 정유라 강제송환\n\n☞ 고화질 감상 https://youtu.be/XjLDue4kpRk", null));
        Collections.sort(DUMMY_BOARDS, new Comparator<BoardPost>() {
            @Override
            public int compare(BoardPost o1, BoardPost o2) {
                return o1.getTime().compareTo(o2.getTime());
            }
        });
        mBoardListAdapter = new BoardListAdapter(DUMMY_BOARDS);

        // RecyclerView 설정
        mBoardList.setHasFixedSize(true);
        mBoardList.setLayoutManager(new LinearLayoutManager(getContext()));
        mBoardList.setAdapter(mBoardListAdapter);
        return view;
    }


    // 게시글 RecyclerView Adapter
    public class BoardListAdapter extends RecyclerView.Adapter<BoardListAdapter.BoardViewHolder> {
        private ArrayList<BoardPost> boardPosts;

        public BoardListAdapter(ArrayList<BoardPost> boardPosts) {
            this.boardPosts = boardPosts;
        }

        @Override
        public BoardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_item, parent, false);
            return new BoardViewHolder(view);
        }

        // View 설정
        @Override
        public void onBindViewHolder(BoardViewHolder holder, int position) {
            BoardPost item = boardPosts.get(position);

            // TITLE 에는 HTML TAG (굵은 글씨, 색상) 적용
            if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
                holder.titleView.setText(Html.fromHtml(item.getTitle(), Html.FROM_HTML_MODE_LEGACY));
            else
                holder.titleView.setText(Html.fromHtml(item.getTitle()));
            holder.timeView.setText(item.getTime());
            holder.contentView.setText(item.getContent());
        }

        @Override
        public int getItemCount() {
            return boardPosts.size();
        }



        // 게시글 뷰 홀더
        class BoardViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.board_item_title) TextView titleView;
            @BindView(R.id.board_item_time) TextView timeView;
            @BindView(R.id.board_item_content) TextView contentView;
            @BindView(R.id.board_item_image) ImageView imageView;
            // OnClick Methods
            @OnClick({R.id.board_item_bt_favorite_mini, R.id.board_item_bt_favorite})
            void OnFavoriteClick(View view) {
                Toast.makeText(getContext(), "좋아요 클릭함.", Toast.LENGTH_SHORT).show();
            }
            @OnClick({R.id.board_item_bt_comment, R.id.board_item_bt_share})
            void OnItemButtonClick(Button button) {
                Toast.makeText(getContext(), button.getText()+" 클릭함.", Toast.LENGTH_SHORT).show();
            }

            public BoardViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
