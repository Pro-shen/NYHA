<template>
  <div class="app-container">
    <div style="display: flex;align-items: center;justify-content: center;font-size: 25px;font-weight: 500;">基于NYHA心功能分级的心力衰竭患者随访信息管理系统</div>
    <div>本系统功能设计：</div>
    <div>----------------------------------------------------------------------------------------------------</div>
    <div>患者</div>
    <div>1.患者能够通过随访系统进行选择医生进行随访</div>
    <div>2.患者可以选择住院</div>
    <div>----------------------------------------------------------------------------------------------------</div>
    <div>医生</div>
    <div>1.医生可以填写就诊内容</div>
    <div>2.医生可以同意患者的随访申请</div>
    <div>----------------------------------------------------------------------------------------------------</div>
    <div>护士</div>
    <div>1.护士能够接受随访内容</div>
    <div>----------------------------------------------------------------------------------------------------</div>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      // 版本号
      version: "3.8.7"
    };
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    }
  }
};
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>

