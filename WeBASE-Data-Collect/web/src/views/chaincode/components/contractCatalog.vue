/*
 * Copyright 2014-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
<template>
    <div class="contract-menu" style="height: 100% ;">
        <div class="contract-menu-header">
            <div>
                <el-tooltip class="item" effect="dark" content="新建文件夹" placement="top-start">
                    <i class="wbs-icon-Addfolder icon contract-icon" @click='addFolder'></i>
                </el-tooltip>
                <el-tooltip class="item" effect="dark" content="上传文件" placement="top-start">
                    <i class="wbs-icon-shangchuan contract-icon" style="position:relative;">
                        <input multiple title="" type="file" id="file" ref='file' name="chaincodes" class="uploads" @change="upload($event)" />
                    </i>
                </el-tooltip>
            </div>
            <div style="margin-left: 5px;">
                <slot name="footer"></slot>
            </div>
            
        </div>
        <div class="contract-menu-content">
            <ul>
                <li v-for='item in contractArry' :key="item.contractId">
                    <div v-if='item.contractType == "file"' class="contract-file" :id='item.contractId'>
                        <i class="wbs-icon-file" @click='select(item)' v-if='!item.renameShow' :id='item.contractId'></i>
                        <span @contextmenu.prevent="handle($event,item)" @click='select(item)' :id='item.contractId' v-if='!item.renameShow' :class="{'colorActive': item.contractActive}">{{item.contractName}}</span>
                        <el-input v-model.trim="contractName" v-focus="true" autofocus='autofocus' maxlength="32" @blur="changeName(item)" v-if="item.renameShow"></el-input>
                        <div class="contract-menu-handle" v-if='item.handleModel' :style="{'top': clentY,'left': clentX}" v-Clickoutside="checkNull">
                            <ul v-if="contractFile">
                                <li class="contract-menu-handle-list" v-if='!item.renameShow && !item.contractAddress' @click="deleteFile(item)">删除</li>
                            </ul>
                        </div>
                    </div>
                    <div v-if='item.contractType == "folder"' class="contract-folder" :id='item.folderId'>
                        <i :class="item.folderIcon" @click='open(item)' v-if="!item.renameShow" :id='item.folderId'></i>
                        <i class="wbs-icon-folder" @contextmenu.prevent="handle($event,item)" v-if="!item.renameShow" style="color: #d19650" :id='item.folderId'></i>
                        <span @contextmenu.prevent="handle($event,item)" :id='item.folderId' v-if="!item.renameShow" :class="{'colorActive': item.contractActive}">{{item.contractName}}</span>
                        <div class="contract-menu-handle" v-if='item.handleModel' :style="{'top': clentY,'left': clentX}" v-Clickoutside="checkNull">
                            <ul>
                                <li class="contract-menu-handle-list" v-if="!item.renameShow" @click='deleteFolder(item)'>删除</li>
                            </ul>
                        </div>
                        <br>
                        <ul v-if="item.folderActive" style="padding-left: 20px;">
                            <li class="contract-file" v-for='list in item.child' :key="list.contractId">
                                <i class="wbs-icon-file" v-if='!list.renameShow' @click='select(list)'></i>
                                <span @click='select(list)' @contextmenu.prevent="handle($event,list)" :id='list.contractId' v-if='!list.renameShow' :class="{'colorActive': list.contractActive}">{{list.contractName}}</span>
                                <el-input v-model.trim="contractName" autofocus='autofocus' maxlength="32" @blur="changeName(list)" v-if="list.renameShow"></el-input>
                                <div class="contract-menu-handle" v-if='list.handleModel' :style="{'top': clentY,'left': clentX}" v-Clickoutside="checkNull">
                                    <ul v-if="contractFile">
                                        <li class="contract-menu-handle-list" v-if='!list.renameShow && !list.contractAddress' @click="deleteFile(list)">删除</li>
                                    </ul>
                                </div>
                            </li>
                        </ul>
                    </div>
                </li>
            </ul>
        </div>
        <add-folder v-if="foldershow" :foldershow="foldershow" @close='folderClose' @success='folderSuccess'></add-folder>
        <add-file v-if="fileshow" :fileshow="fileshow" @close='fileClose' @success='fileSucccess($event)' :id='folderId'></add-file>
        <select-catalog v-if='cataLogShow' :show='cataLogShow' @success='catalogSuccess($event)' @close='catalogClose'></select-catalog>
    </div>
</template>
<script>
import addFolder from "../dialog/addFolder"
import addFile from "../dialog/addFile"
import selectCatalog from "../dialog/selectCatalog"
import { getContractList, saveChaincode, deleteCode } from "@/util/api"
import Bus from '@/bus'
import errcode from "@/util/errcode";
import Clickoutside from 'element-ui/src/utils/clickoutside'
export default {
    name: "contractCatalog",
    components: {
        "add-folder": addFolder,
        "add-file": addFile,
        "select-catalog": selectCatalog,
    },
    data: function () {
        return {
            foldershow: false,
            fileshow: false,
            filename: "",
            fileString: "",
            contractList: [],
            folderList: [],
            contractArry: [],
            contractData: null,
            cataLogShow: false,
            realContractList: [],
            contractName: "",
            clentX: 0,
            clentY: 0,
            contractFile: false,
            contractFolder: false,
            ID: "",
            handleModel: false,
            folderId: null
        }
    },
    beforeDestroy: function () {
        Bus.$off("compile")
        Bus.$off("deploy")
        Bus.$off("open")
        Bus.$off("save")
    },
    mounted: function () {
        this.$nextTick(function () {
            this.getContracts()
        })
        Bus.$on("compile", data => {
            this.saveContract(data, '合约编译成功！')
        })
        Bus.$on("save", data => {
            this.saveContract(data)
        })
        Bus.$on("deploy", data => {
            this.getContracts(data);
        })
        Bus.$on("open", data => {
            this.contractArry.forEach(value => {
                if (value.contractName == data.contractPath && !value.folderActive) {
                    this.$set(value, 'folderActive', true)
                    this.$set(value, 'folderIcon', 'el-icon-caret-bottom')
                }
            })
            this.select(data)
        })
        Bus.$on("send", data => {
            this.contractArry.forEach(value => {
                if (value.contractId == data.contractId) {
                    this.$set(value, 'contractAddress', data.contractAddress)
                } else if (value.contractType == 'folder') {
                    value.child.forEach(list => {
                        if (list.contractId == data.contractId) {
                            this.$set(list, 'contractAddress', data.contractAddress)
                        }
                    })
                }
            })
        })
    },
    directives: {
        Clickoutside,
        focus: {
            update: function (el, { value }) {
                if (value) {
                    el.focus()
                }
            }
        }
    },
    methods: {
        checkNull: function (list) {
            this.contractArry.forEach(value => {
                value.handleModel = false;
                if (value.contractType == 'folder') {
                    value.child.forEach(list => {
                        list.handleModel = false;
                    })
                }
            })
            this.ID = "";
            this.contractFile = false;
            this.contractFolder = false;
            this.handleModel = false;
        },
        handle: function (e, list) {
            this.checkNull();
            list.handleModel = true
            if (e.clientX > 201) {
                this.clentX = e.clientX - 200 + 'px';
            } else {
                this.clentX = e.clientX + 'px';
            }
            this.clentY = 20 + 'px';
            this.ID = e.target.id;
            let item = {};
            if (this.ID) {
                this.handleModel = true;
                this.contractArry.forEach(value => {
                    if ((value.contractId && value.contractId == this.ID) || (value.folderId && value.folderId == this.ID)) {
                        item = value
                    } else if (value.contractType == 'folder') {
                        value.child.forEach(list => {
                            if (list.contractId == this.ID) {
                                item = list
                            }
                        })
                    }
                })
                if (item.contractType == 'file') {
                    this.contractFile = true;
                    this.contractFolder = false;
                } else {
                    this.contractFile = false;
                    this.contractFolder = true;
                }
            } else {
                this.ID = "";
                this.contractFile = false;
                this.contractFolder = false;
                this.handleModel = false;
            }
        },
        rename: function (val) {
            this.contractArry.forEach(value => {
                value.handleModel = false;
                if (value.contractType == 'folder') {
                    value.child.forEach(list => {
                        list.handleModel = false;
                    })
                }
            })
            this.contractArry.forEach(value => {
                if (value.contractId == this.ID && value.contractStatus != 2) {
                    this.$set(value, 'renameShow', true)
                    this.contractName = value.contractName;
                } else if (value.contractType == 'folder' && value.folderId !== this.ID) {
                    value.child.forEach(item => {
                        if (item.contractId == this.ID && item.contractStatus != 2) {
                            this.$set(item, 'renameShow', true)
                            this.contractName = item.contractName
                        } else if (item.contractId == this.ID && item.contractStatus == 2) {
                            this.$set(item, 'renameShow', false)
                            this.$message({
                                message: '已部署的合约不能重新命名！',
                                type: "error"
                            });
                        } else {
                            this.$set(item, 'renameShow', false)
                        }
                    })
                } else if (value.contractType == 'folder' && value.folderId == this.ID) {
                    let num = 0
                    value.child.forEach(item => {
                        if (item.contractStatus == 2) {
                            num++
                        }
                    })
                    if (num == 0) {
                        this.$set(value, 'renameShow', true)
                        this.contractName = value.contractName
                    }
                } else if (value.contractId == this.ID && value.contractStatus == 2) {
                    this.$set(value, 'renameShow', false)
                    this.$message({
                        message: '已部署的合约不能重新命名！',
                        type: "error"
                    });
                } else {
                    this.$set(value, 'renameShow', false)
                }
            })

            this.contractFile = false;
            this.contractFolder = false;
            this.handleModel = false;
        },
        changeName: function (val) {
            let pattern = /^[A-Za-z0-9_]+$/
            if (pattern.test(this.contractName) && this.contractName.length < 32 && this.contractName.length > 1) {
                if (this.contractName !== val.contractName) {
                    for (let i = 0; i < this.contractList.length; i++) {
                        if (this.contractList[i].contractName == this.contractName && this.contractList[i].contractPath == val.contractPath) {
                            this.$message({
                                message: '同目录下存在相同的合约，请重新命名!',
                                type: "error"
                            });
                            this.$set(val, 'renameShow', false)
                            return
                        }
                    }
                    if (this.contractName) {
                        this.$set(val, 'contractName', this.contractName)
                        this.contractName = ""
                        this.saveContract(val);
                    } else {
                        this.$set(val, 'renameShow', false)
                    }
                } else {
                    this.$set(val, 'renameShow', false)
                }
            } else {
                this.$message({
                    message: '请输入数字或字母,长度为1到32位！',
                    type: "error"
                });
                this.$set(val, 'renameShow', false)
            }
        },
        addFolder: function () {
            this.checkNull();
            this.foldershow = true
        },
        addFile: function () {
            this.checkNull();
            this.fileshow = true
        },
        addFiles: function () {
            this.fileshow = true;
            this.folderId = this.ID;
            this.ID = "";
            this.contractFile = false;
            this.contractFolder = false;
            this.handleModel = false;
        },
        upload: function (e) {
            this.checkNull();
            if (!e.target.files.length) {
                return;
            }
            this.uploadFiles = e.target.files;

            for (let i = 0; i < this.uploadFiles.length; i++) {
                let filessize = Math.ceil(this.uploadFiles[i].size / 1024);
                let filetype = this.uploadFiles[i].name.split(".")[1];
                if (filessize > 400) {
                    this.$message({
                        message: '文件大小超过400k，请上传小于400k的文件!',
                        type: "error"
                    });
                    this.cataLogShow = false
                    break;
                } else if (filetype !== "sol") {
                    this.$message({
                        message: '请上传.sol格式的文件!',
                        type: "error"
                    });
                    this.cataLogShow = false
                    break;
                } else {
                    this.cataLogShow = true
                }
            }
        },
        catalogSuccess: function (val) {
            for (let i = 0; i < this.uploadFiles.length; i++) {
                let reader = new FileReader(); //新建一个FileReader
                reader.readAsText(this.uploadFiles[i], "UTF-8"); //读取文件
                let filename = "", _this = this;
                filename = this.uploadFiles[i].name.split(".")[0];
                let num = 0;
                this.contractList.forEach(value => {
                    if (value.contractName == filename && value.contractPath == val && num === 0) {
                        this.$message({
                            type: "error",
                            message: '同一目录下不能存在同名合约!'
                        });
                        num++;
                    }
                });
                if (!num) {
                    reader.onload = function (evt) {
                        var fileString = "";
                        fileString = Base64.encode(evt.target.result); // 读取文件内容
                        let data = {
                            contractName: filename,
                            contractSource: fileString,
                            contractPath: val,
                            contractType: "file",
                            contractActive: false,
                            contractstatus: 0,
                            contractAbi: "",
                            runtimeBin: "",
                            contractAddress: "",
                            contractVersion: "",
                            contractNo: new Date().getTime()
                        };
                        _this.saveContract(data);
                    };
                }
            }
            this.$refs.file.value = "";
            this.catalogClose();
        },
        catalogClose: function () {
            this.cataLogShow = false
        },
        folderClose: function () {
            this.foldershow = false
        },
        folderSuccess: function () {
            this.folderClose()
            this.getContractArry();
        },
        fileClose: function () {
            this.fileshow = false
            this.folderId = ""
            this.ID = ""
        },
        getContractArry: function (val) {
            let result = [];
            let list = [];
            let folderArry = this.createFolder();
            let newFileList = [];
            list = this.contractList || []
            list.forEach(value => {
                this.$set(value, 'handleModel', false)
                if (value.contractPath == "/") {
                    newFileList.push(value)
                }
            })
            folderArry.forEach(value => {
                this.$set(value, 'handleModel', false)
                value.child = [];
                list.forEach((item, index) => {
                    if (item.contractPath == value.contractName) {
                        value.child.push(item)
                    }
                })
            })
            result = newFileList.concat(folderArry);
            this.contractArry = result;
            if (this.contractList.length && !val) {
                this.select(this.contractList[0])
            } else if (val && this.contractList.length) {
                this.select(val)
            } else {
                Bus.$emit("noData", true)
            }
        },
        saveContract: function (data, title) {
            let reqData = {
                chainId: localStorage.getItem("chainId"),
                groupId: localStorage.getItem("groupId"),
                contractName: data.contractName,
                contractPath: data.contractPath,
                contractSource: data.contractSource,
                contractAbi: data.contractAbi,
                runtimeBin: data.runtimeBin,
                bytecodeBin: data.bytecodeBin,
            }
            if (data.contractId) {
                reqData.contractId = data.contractId
            }
            saveChaincode(reqData).then(res => {
                if (res.data.code === 0) {
                    this.getContracts(res.data.data);
                    if (data.contractId) {
                        this.$message({
                            type: "success",
                            message: title || '合约保存成功！'
                        });
                    }
                } else {
                    this.$message({
                        message: this.$chooseLang(res.data.code),
                        type: "error",
                        duration: 2000
                    });
                }
            })
                .catch(err => {
                    this.$message({
                        message: '系统错误',
                        type: "error",
                        duration: 2000
                    });

                });
        },
        getContracts: function (list) {
            let data = {
                chainId: localStorage.getItem("chainId"),
                groupId: localStorage.getItem("groupId"),
                pageNumber: 1,
                pageSize: 500,
            }
            getContractList(data).then(res => {
                if (res.data.code == 0) {
                    this.contractList = res.data.data || [];
                    localStorage.setItem("contractList", JSON.stringify(this.contractList))
                    if (res.data.data.length) {
                        if (localStorage.getItem("folderList")) {
                            this.folderList = JSON.parse(localStorage.getItem("folderList"))
                        } else {
                            this.folderList = []
                        }
                        this.contractList.forEach(value => {
                            if (value.contractPath != "/") {
                                let item = {
                                    folderName: value.contractPath,
                                    folderId: (new Date()).getTime() + + `${value.contractPath}`,
                                    folderActive: false,
                                    groupId: localStorage.getItem("groupId")
                                }
                                this.folderList.push(item)
                            }
                        });
                        let result = [];
                        let arrry = []
                        let obj = {};
                        for (let i = 0; i < this.folderList.length; i++) {
                            if (!obj[this.folderList[i].folderName] && this.folderList[i].groupId == localStorage.getItem("groupId")) {
                                result.push(this.folderList[i]);
                                obj[this.folderList[i].folderName] = true;
                            } else if (this.folderList[i].groupId != localStorage.getItem("groupId")) {
                                arrry.push(this.folderList[i]);
                            }
                        }
                        this.folderList = result.concat(arrry);
                        localStorage.setItem("folderList", JSON.stringify(this.folderList))
                        this.contractList.forEach(value => {
                            this.$set(value, "contractType", 'file')
                            this.$set(value, "contractActive", false)
                            this.$set(value, "renameShow", false)
                            this.$set(value, "inputShow", false)
                        })
                        if (list) {
                            this.getContractArry(list);
                        } else if (this.$route.query.contractId) {
                            let num = 0;
                            this.contractList.forEach(value => {
                                if (value.contractId == this.$route.query.contractId) {
                                    num++
                                    this.getContractArry(value);
                                }
                                if (!num) {
                                    this.getContractArry()
                                }
                            })
                        } else {
                            this.getContractArry()
                        }
                    } else {
                        if (localStorage.getItem("folderList")) {
                            this.folderList = JSON.parse(localStorage.getItem("folderList"))
                            this.getContractArry()
                        }
                    }
                } else {
                    this.$message({
                        message: this.$chooseLang(res.data.code),
                        type: "error",
                        duration: 2000
                    });
                }
            })
                .catch(err => {
                    this.$message({
                        message: '系统错误',
                        type: "error",
                        duration: 2000
                    });
                });
        },
        fileSucccess: function (val) {
            let num = 0
            this.contractList.forEach(value => {
                if (value.contractName == val.contractName && value.contractPath == val.contractPath) {
                    this.$message({
                        type: "error",
                        message: "同一目录下不能存在同名合约!"
                    });
                    num++
                }
            })
            if (!num) {
                this.saveContract(val)
            }
            this.fileClose()
        },
        createFolder: function () {
            let result = []
            if (localStorage.getItem("folderList")) {
                this.folderList = JSON.parse(localStorage.getItem("folderList"))
            } else {
                this.folderList = []
            }
            this.folderList.forEach(value => {
                let num = 0
                if (!value.groupId || value.groupId && localStorage.getItem("groupId") && value.groupId == localStorage.getItem("groupId")) {
                    let data = {
                        contractName: value.folderName,
                        folderId: value.folderId,
                        contractActive: false,
                        contractType: 'folder',
                        folderIcon: "el-icon-caret-bottom",
                        folderActive: true,
                        renameShow: false,
                        inputShow: false
                    }
                    this.contractArry.forEach(item => {
                        if (item.contractType == 'folder' && item.folderId == value.folderId) {
                            data.folderIcon = item.folderIcon;
                            data.folderActive = item.folderActive;
                        }
                    })
                    result.push(data);
                }
            })
            return result
        },
        open: function (val) {
            this.contractArry.forEach(value => {
                this.$set(value, 'contractActive', false)
                if (value.contractType == 'folder') {
                    value.child.forEach(item => {
                        this.$set(item, 'contractActive', false)
                    })
                }
            })
            if (val.folderActive) {
                this.$set(val, 'folderActive', false)
                this.$set(val, 'folderIcon', 'el-icon-caret-right')
            } else {
                this.$set(val, 'folderActive', true)
                this.$set(val, 'folderIcon', 'el-icon-caret-bottom')
            }
            this.$set(val, 'contractActive', true)
        },
        select: function (val) {
            let num = 0;
            this.contractArry.forEach(value => {
                if (value.contractId == val.contractId) {
                    this.$set(value, 'contractActive', true)
                } else if (value.contractType == 'folder') {
                    this.$set(value, 'contractActive', false)
                    value.child.forEach(item => {
                        if (item.contractId == val.contractId) {
                            this.$set(item, 'contractActive', true)
                        } else {
                            this.$set(item, 'contractActive', false)
                        }
                    })
                } else {
                    this.$set(value, 'contractActive', false)
                }
            })
            Bus.$emit('select', val)
        },
        deleteFile: function (val) {
            this.$confirm('确认删除？')
                .then(_ => {
                    this.deleteData(val)
                })
                .catch(_ => { });

        },
        deleteData: function (val) {
            let data = {
                contractId: val.contractId
            }
            deleteCode(data, {}).then(res => {
                if (res.data.code === 0) {
                    this.getContracts()
                } else {
                    this.$message({
                        message: this.$chooseLang(res.data.code),
                        type: "error",
                        duration: 2000
                    });
                }
            })
                .catch(err => {
                    this.loading = false;
                    this.$message({
                        message: "系统错误",
                        type: "error",
                        duration: 2000
                    });
                });
        },
        deleteFolder: function (val) {
            this.$confirm("确认删除？")
                .then(_ => {
                    this.deleteFolderData(val)
                })
                .catch(_ => { });
        },
        deleteFolderData: function (val) {
            let list = val.child;
            let num = 0;
            for (let i = 0; i < list.length; i++) {
                if (list[i].contractStatus == 2) {
                    this.$message({
                        type: "error",
                        message: "文件夹内存在已部署的合约，无法删除文件夹!"
                    })
                    return
                } else {
                    num++
                }
            }
            if (num) {
                while (list.length > 0) {
                    this.deleteData(list[0])
                    list.splice(0, 1)
                }
            }
            if (val.child.length == 0) {
                this.folderList = JSON.parse(localStorage.getItem("folderList"))
                this.folderList.forEach((value, index) => {
                    if (val.folderId == value.folderId) {
                        this.folderList.splice(index, 1)
                    }
                })
                localStorage.setItem("folderList", JSON.stringify(this.folderList))
                this.getContracts()
            }
        }
    }
}
</script>
<style scoped>
.icon {
    font-weight: bold;
}
.contract-menu-header {
    width: calc(100% + 1px);
    height: 48px;
    line-height: 48px;
    border-bottom: 2px solid #e7ebf0;
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
}
.contract-icon {
    vertical-align: middle;
    padding-left: 10px;
    cursor: pointer;
}
.checkContract-upload {
    display: block;
    position: absolute;
    height: 30px;
    left: 0;
    margin-top: -30px;
    width: 100%;
    opacity: 0;
    /* -ms-filter: "alpha(opacity=0)"; */
    z-index: 9;
    cursor: pointer;
}
.contract-file {
    position: relative;
    padding-left: 25px;
}
.contract-folder {
    position: relative;
    padding-left: 5px;
}
.contract-folder i {
    cursor: pointer;
}
.contract-file span {
    cursor: pointer;
}
.contract-file i {
    cursor: pointer;
}
.uploads {
    position: absolute;
    width: 18px;
    height: 18px;
    left: 10px;
    top: 36px;
    opacity: 0;
    z-index: 999;
    cursor: pointer;
}
.colorActive {
    color: rgb(55, 238, 242);
}
.contract-delete {
    padding-left: 20px;
    font-weight: 100;
    font-size: 16px;
}
.contract-file-handle {
    position: absolute;
    width: 60px;
    top: 24px;
    padding: 10px;
    background-color: #fff;
    z-index: 9999;
    box-shadow: 1px 1px 1px;
}
.contract-menu-content {
    overflow: auto;
    height: calc(100% - 50px);
}
.contract-menu-content >>> .el-input__inner {
    width: 100px;
    height: 24px;
    line-height: 24px;
    padding: 0 5px;
}
.contract-menu-handle {
    position: absolute;
    font-size: 0;
    width: 100px;
    cursor: pointer;
    font-size: 12px;
    text-align: center;
    vertical-align: middle;
    background-color: #fff;
    box-shadow: 1px 4px 4px 1px;
    z-index: 9999;
}
.contract-menu-handle li {
    font-size: 12px;
    height: 30px;
    line-height: 30px;
    padding-left: 8px;
}
.contract-menu-handle-list {
    cursor: pointer;
    color: #666;
}
.contract-menu-handle-list:hover {
    color: rgb(55, 238, 242);
}
</style>


