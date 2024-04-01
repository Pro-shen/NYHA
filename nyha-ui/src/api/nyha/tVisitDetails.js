import request from "@/utils/request";
import x86asm from "highlight.js/lib/languages/x86asm";

export function list(query) {
  return request({
    url: "/nyha/tVisitDetails/list",
    method: "get",
    params: query,
  });
}

export function getUserId(userName) {
  return request({
    url: "/nyha/tVisitDetails/getUserId/" + userName,
    method: "get",
  });
}

export function add(data) {
  return request({
    url: "/nyha/tVisitDetails/add",
    method: "post",
    data: data,
  });
}

export function selectTVisitListById(id) {
  return request({
    url: "/nyha/tVisitDetails/selectTVisitListById/" + id,
    method: "get",
  });
}

export function edit(data) {
  return request({
    url: "/nyha/tVisitDetails/edit",
    method: "post",
    data: data,
  });
}

export function remove(data) {
  return request({
    url: "/nyha/tVisitDetails/remove",
    method: "post",
    data: data,
  });
}
