<template>
  <div class="comment-container">
      <el-input
          v-model="comment"
          :rows="3"
          type="textarea"
          placeholder="Please input"
          style="margin-bottom: 10px"
      />
      <el-button type="default" @click="onSubmit">提交评论</el-button>
      <div class="comment-list">
        <div class="comment-wrapper">
          <div class="comment-item">
            <el-avatar class="comment-avatar" :size="50" src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" />
            <div class="comment-content">
              <div class="content-top">
                <div class="comment-info">
                  <div class="name">路人甲</div>
                  <div class="date">2022</div>
                </div>
                <div class="comment-reply">
                  o
                </div>
              </div>
              <div class="content-bottom">
                hello world
              </div>
            </div>
          </div>
        </div>
        <div class="comment-wrapper">
          <div class="comment-item">
            <el-avatar class="comment-avatar" :size="50" src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" />
            <div class="comment-content">
              <div class="content-top">
                <div class="comment-info">
                  <div class="name">路人甲</div>
                  <div class="date">2022</div>
                </div>
                <div class="comment-reply">
                  o
                </div>
              </div>
              <div class="content-bottom">
                hello world
              </div>
            </div>
          </div>
        </div>
        <div class="comment-wrapper">
          <div class="comment-item">
            <el-avatar class="comment-avatar" :size="50" src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" />
            <div class="comment-content">
              <div class="content-top">
                <div class="comment-info">
                  <div class="name">路人甲</div>
                  <div class="date">2022</div>
                </div>
                <div class="comment-reply">
                  o
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

<script setup>

const comment = ref("说点什么吧")
const onSubmit = () => {

}
</script>

<style scoped lang="scss">
.comment-list {
  color: #758397;
}
.comment-wrapper {
  padding: 0.5em 0;
}
.comment-item {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
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
</style>
