export interface CommentInfo {
    id?: number;
    blogId?: number;
    parentId?: number;
    rootId?:number;
    state?: number;
    commentatorName: string;
    content: string;
    createTime?: string;
    child?: CommentInfo[];
    inputActive?: boolean;
}
