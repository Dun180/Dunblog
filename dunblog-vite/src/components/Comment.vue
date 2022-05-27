<template>
  <div class="comment-container">
    <div class="comment-input">
      <el-input
          v-model="comment.content"
          :rows="3"
          type="textarea"
          placeholder="说点什么吧"

      />
      <div class="submit-area">
        <el-popover placement="top" :width="200" trigger="click" popper-class="popover-wrap">
          <template #reference>
            <el-button plain>
              <i class="iconfont icon-yonghu-xianxing" style="margin-right: 0.5em"></i>
              {{ comment.commentatorName || "昵称" }}
            </el-button>
          </template>
          <div class="popover">
            <div class="title">
              访客信息
            </div>
            <div class="content">
              <i class="iconfont icon-yonghu-xianxing"></i>
              <el-input v-model="comment.commentatorName" placeholder="昵称" />
            </div>
          </div>
        </el-popover>

        <el-button class="button" type="default" @click="onSubmit">提交评论</el-button>
      </div>
    </div>

    <div class="comment-list">
      <div class="comment-item">
        <div class="comment-wrapper">
          <el-avatar class="comment-avatar" :size="50" src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" />
          <div class="comment-content">
            <div class="content-top">
              <div class="comment-info">
                <div class="name">路人甲</div>
                <div class="date">2022</div>
              </div>
              <div class="comment-reply">
                <i class="iconfont icon-icon_reply"></i>
              </div>
            </div>
            <div class="content-bottom">
              hello world
            </div>

            <div class="comment-input">
              <el-input
                  v-model="comment.content"
                  :rows="3"
                  type="textarea"
                  placeholder="说点什么吧"
              />
              <div class="submit-area">
                <el-popover placement="top" :width="200" trigger="click" popper-class="popover-wrap">
                  <template #reference>
                    <el-button plain>
                      <i class="iconfont icon-yonghu-xianxing" style="margin-right: 0.5em"></i>
                      {{ comment.commentatorName || "昵称" }}
                    </el-button>
                  </template>
                  <div class="popover">
                    <div class="title">
                      访客信息
                    </div>
                    <div class="content">
                      <i class="iconfont icon-yonghu-xianxing"></i>
                      <el-input v-model="comment.commentatorName" placeholder="昵称" />
                    </div>
                  </div>
                </el-popover>

                <el-button class="button" type="default" @click="onSubmit">回复</el-button>
              </div>
            </div>

          </div>
        </div>
      </div>
      <div class="comment-item sub-list">
        <div class="comment-wrapper">
          <el-avatar class="comment-avatar" :size="50" src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" />
          <div class="comment-content">
            <div class="content-top">
              <div class="comment-info">
                <div class="name">路人甲</div>
                <div class="date">2022</div>
              </div>
              <div class="comment-reply">
                <i class="iconfont icon-icon_reply"></i>
              </div>
            </div>
            <div class="content-bottom">
              hello world
            </div>
          </div>
        </div>
      </div>
      <div class="comment-item">
        <div class="comment-wrapper">
          <el-avatar class="comment-avatar" :size="50" src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" />
          <div class="comment-content">
            <div class="content-top">
              <div class="comment-info">
                <div class="name">路人甲</div>
                <div class="date">2022</div>
              </div>
              <div class="comment-reply">
                <i class="iconfont icon-icon_reply"></i>
              </div>
            </div>
            <div class="content-bottom">
              hello world
            </div>
          </div>
        </div>
      </div>

    </div>
  </div>
</template>

<script setup lang="ts">
import {onMounted, ref} from "vue";
import {ElMessage} from "element-plus";
import {getCommentListByBlogId} from "@/lib/api";
import {CommentInfo} from "@/models/comment";
const props = defineProps({
  blogId: Number
})
const comment = ref({
  commentatorName:'',
  content:'',
  blogId:0
})

const commentList = ref([] as CommentInfo[])

const onSubmit = () => {
  if (props.blogId) {
    comment.value.blogId = props.blogId
  }
  if (!comment.value.content){
    ElMessage({
      message: '请填写评论内容',
      type: 'warning',
    })
    return
  }
  if(!comment.value.constructor){
    comment.value.commentatorName = '匿名游客'
  }

}
onMounted(async () => {
  if (props.blogId) {
    const resp = await getCommentListByBlogId(props.blogId);
    if (resp.code == 200){
      commentList.value = resp.data
    }
  }
})
</script>

<style scoped lang="scss">
.comment-input {
  margin: 0.5em 0;
}
.submit-area {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  margin: 0.5em 0;
  .input {
    width: 25%;
  }
  .button {
    margin-left: auto;
  }
}
.comment-list {
  color: #758397;
  .sub-list {
    padding-left: 1.5em;
  }
}
.comment-wrapper {
  padding: 0.5em 0;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.comment-item {

}
.comment-avatar {
  margin-right: 1em;
}
.comment-content {
  flex-grow: 1;

  .content-top {
    display: flex;
    align-items: start;
  }
  .content-bottom {
    padding: 0.25em 0;
    display: flex;
    justify-content: flex-start;
  }
}
.comment-info {
  border-bottom: 1px solid #E5E9F2;
  line-height: 1.5em;
  display: flex;
  flex-grow: 1;
  justify-content: space-between;
  align-items: center;

  .name {
    font-weight: 700;
    box-shadow: 0 1px #99A9BF;
    color: #475669;
  }
  .date {
    font-size: 14px;
    color: #C0CCDA;
    margin-right: 1em;
  }
}
.comment-reply {
  cursor: pointer;
  transform: translateY(0.75em);
  color: #C0CCDA;
  border-radius: 9em;
  background: linear-gradient(#fff,#EFF2F7) no-repeat;
  height: 1.5em;
  padding: 0 0.625em;
  border: 1px solid #D3DCE6;
  box-shadow: 0 4px 6px rgba(31,45,61,.1),0 0 0 1px #fff inset,0.75em 0 0.75em transparent inset;
  transition: .35s;
}
.comment-reply:hover {
  border: 1px solid #33bbff;
  background: linear-gradient(90deg,#1f8bff,#33bbff);
  color: #fff;
  text-shadow: 0 2px 2px #1f8bff;
  box-shadow: 0 5px 8px rgba(31,139,255,.5);
}
.comment-reply {
  display: flex;
  justify-content: center;
  align-items: center;
}
.popover {
  display: flex;
  flex-direction: column;
  .title {
    border-radius: 0.5em 0.5em 0 0;
    padding: 0.375rem 1rem;
    border: 1px solid #fff;
    border-bottom-color: #E5E9F2;
    background: #f2f7fc;
    color: #475669;
  }
  .content {
    padding: 0.75rem 1rem;
    display: flex;
    flex-direction: row;
    .iconfont {
      font-size: 1.5em;
      margin-right: 0.5em;
    }
  }
}
</style>

<style>
.el-popover.popover-wrap {
  padding: 0;
}
</style>
