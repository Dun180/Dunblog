<template>
  <div class="comment-container">
    <div class="comment-input" v-show="inputActive">
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

        <el-button class="button" type="default" @click="onSubmit(null)">提交评论</el-button>
      </div>
    </div>

    <div class="comment-list">
      <div class="comment-item"
          v-for="(root, index) in commentList"
          :key="index"
      >
        <div class="comment-wrapper">
          <el-avatar class="comment-avatar" :size="50" src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" />
          <div class="comment-content">
            <div class="content-top">
              <div class="comment-info">
                <div class="name">{{root.commentatorName}}</div>
                <div class="date">
                  {{ moment(root.createTime).format('YYYY-MM-DD') }}
                </div>
              </div>
              <div class="comment-reply" @click="handleReply(root)">
                <i class="iconfont icon-icon_reply"></i>
              </div>
            </div>
            <div class="content-bottom">
              {{ root.content }}
            </div>

            <div class="comment-input" v-show="root.inputActive">
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
                <div class="button">
                  <el-link class="link" type="primary" @click="handleCencelReply()">取消回复</el-link>
                  <el-button type="default" @click="onSubmit(root)">回复</el-button>
                </div>
              </div>
            </div>

          </div>
        </div>
        <div class="comment-item sub-list"
            v-for="(item, index) in root.child"
            :key="index"
        >
          <div class="comment-wrapper">
            <el-avatar class="comment-avatar" :size="50" src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" />
            <div class="comment-content">
              <div class="content-top">
                <div class="comment-info">
                  <div class="name">{{item.commentatorName}}</div>
                  <div class="date">
                    {{ moment(item.createTime).format('YYYY-MM-DD') }}
                  </div>
                </div>
                <div class="comment-reply" @click="handleReply(item)">
                  <i class="iconfont icon-icon_reply"></i>
                </div>
              </div>
              <div class="content-bottom">
                {{ item.content }}
              </div>
              <div class="comment-input" v-show="item.inputActive">
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
                  <div class="button">
                    <el-link class="link" type="primary" @click="handleCencelReply()">取消回复</el-link>
                    <el-button type="default" @click="onSubmit(root)">回复</el-button>
                  </div>
                </div>
              </div>

            </div>
          </div>
        </div>

      </div>

    </div>
  </div>
</template>

<script setup lang="ts">
import {inject, onMounted, ref} from "vue";
import {ElMessage} from "element-plus";
import moment from "moment";
import {addComment, queryComment} from "@/lib/api";
import {CommentInfo} from "@/models/comment";

const reload = inject("frontReload", Function)

const props = defineProps({
  blogId: Number
})
const comment = ref<CommentInfo>({
  commentatorName:'',
  content:'',
})
const inputActive = ref(true)
const lastInputItem = ref<CommentInfo|null>(null)
const commentList = ref([] as CommentInfo[])

const onSubmit = async (root: CommentInfo|null) => {
  //对评论内容进行校验
  if (!comment.value.content){
    ElMessage({
      message: '请填写评论内容',
      type: 'warning',
    })
    return
  }
  if(!comment.value.commentatorName){
    comment.value.commentatorName = '匿名游客'
  }
  if (props.blogId) { //设置博客id
    comment.value.blogId = props.blogId
  }
  if (root) {  //如果不是根评论，设置根评论id
    comment.value.rootId = root.id
  }
  if (lastInputItem.value) {  //如果是回复评论，设置父评论id
    comment.value.parentId = lastInputItem.value.id
    comment.value.content = '回复 '+lastInputItem.value.commentatorName + ' :' + comment.value.content
  }


  const resp = await addComment(comment.value)
  if (resp.code == 200) {
    reload()
    handleCencelReply()
    ElMessage({
      message: '评论成功',
      type: 'success',
    })
  }else {
    ElMessage({
      message: '评论失败',
      type: 'error',
    })
  }

}

const handleReply = (item: CommentInfo) => {
  if (lastInputItem.value?.inputActive){
    lastInputItem.value.inputActive = false
  }
  item.inputActive = true;
  if (inputActive.value) {
    inputActive.value = false;
  }
  lastInputItem.value = item;
}

const handleCencelReply = () => {
  if (lastInputItem.value?.inputActive){
    lastInputItem.value.inputActive = false
  }
  inputActive.value = true;
  lastInputItem.value = null;

}
onMounted(async () => {
  if (props.blogId) {
    // const resp = await getCommentListByBlogId(props.blogId);
    const resp = await queryComment({blogId:props.blogId,state:1})

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
    .link {
      margin-right: 1em;
    }
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
