import request from "@/utils/request";
import x86asm from "highlight.js/lib/languages/x86asm";

export function list(query) {
  return request({
    url: "/nyha/tVisitRequest/list",
    method: "get",
    params: query,
  });
}

export function getUserId(userName) {
  return request({
    url: "/nyha/tVisitRequest/getUserId/" + userName,
    method: "get",
  });
}

export function add(data) {
  return request({
    url: "/nyha/tVisitRequest/add",
    method: "post",
    data: data,
  });
}

export function selectTVisitListById(id) {
  return request({
    url: "/nyha/tVisitRequest/selectTVisitListById/" + id,
    method: "get",
  });
}

export function edit(data) {
  return request({
    url: "/nyha/tVisitRequest/edit",
    method: "post",
    data: data,
  });
}

export function remove(data) {
  return request({
    url: "/nyha/tVisitRequest/remove",
    method: "post",
    data: data,
  });
}
