<template>
    <div>
        <p>
            <button v-on:click="add()" class="btn btn-white btn-default btn-round">
            <i class="ace-icon fa fa-edit"></i>
            新增
            </button>
            &nbsp; &nbsp;
            <button v-on:click="list(1)" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-refresh"></i>
                刷新
            </button>
        </p>
        <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>
    <table id="simple-table" class="table  table-bordered table-hover">
                <thead>
                <tr>
                    <th >id</th>
                    <th>名称</th>
                    <th>课程id</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="chapter in chapters">
                    <td>{{chapter.id}}</td>
                    <td> {{chapter.name}}</td>
                    <td> {{chapter.courseId}}</td>
                    <td>
                        <div class="hidden-sm hidden-xs btn-group">
                         <!--   <button v-on:click="toSection(chapter)" class="btn btn-white btn-xs btn-info btn-round">
                                小节
                            </button>&nbsp;-->
                            <button v-on:click="edit(chapter)" class="btn btn-white btn-xs btn-info btn-round">
                                编辑
                            </button>&nbsp;
                            <button v-on:click="del(chapter.id)" class="btn btn-white btn-xs btn-warning btn-round">
                                删除
                            </button>
                        </div>
                    </td>
                </tr>
                </tbody>
    </table>
        <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">表单</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">名称</label>
                                <div class="col-sm-10">
                                    <input v-model="chapter.name" class="form-control" placeholder="名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label  class="col-sm-2 control-label">课程id</label>
                                <div class="col-sm-10">
                                    <input type="text"  v-model="chapter.courseId" class="form-control" placeholder="课程id">
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button"  @click="save()" class="btn btn-primary">保存</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
    </div>
</template>

<script>
    import Pagination from "../../components/pagination";
    export default {
        name: "chapter",
        components: {
            Pagination
        },
        data:function(){
         return{
             chapter: {},
             chapters:[],

         }
        },
        mounted() {
            let _this=this;
            _this.list(1);
        },
        methods:{
            add(){
                let _this=this;
                _this.chapter = {};
                $("#form-modal").modal("show");
            },

            edit(chapter){
                let _this=this;
                _this.chapter = $.extend({},chapter);
                $("#form-modal").modal("show");
            },


            list(page){
              let _this=this;
              _this.$ajax.post('http://127.0.0.1:9000/business/admin/chapter/list',{
                  page:page,
                  size:_this.$refs.pagination.size,
              }).then((response)=>{
                  console.log("查询大章结果",response);
                   let res=response.data;
                  _this.chapters=res.content.list;
                  _this.$refs.pagination.render(page,res.content.total);
              })
          },

            save(page){
                let _this=this;
                // 保存校验
                if (!Validator.require(_this.chapter.name, "名称")
                    || !Validator.length(_this.chapter.courseId, "课程ID", 1, 8)) {
                    return;
                }
                _this.$ajax.post('http://127.0.0.1:9000/business/admin/chapter/save',_this.chapter).then((response)=>{
                    console.log("添加大章结果",response);
                    let res=response.data;
                    if(res.success){
                        $("#form-modal").modal("hide");
                       _this.list(1);
                        Toast.success("保存成功");
                    }else{
                        Toast.warning(res.message)
                    }
                })
            },
            del(id){
                let _this=this;
                Confirm.show("删除大章后不可恢复",function () {
                    _this.$ajax.delete('http://127.0.0.1:9000/business/admin/chapter/delete/'+id).then((response)=>{
                        console.log("删除大章结果",response);
                        let res=response.data;
                        if(res.success){
                            _this.list(1);
                            Toast.success("删除成功");
                        }
                    })

                });
            }
        }

    }
</script>
