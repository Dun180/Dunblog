export interface CommentInfo {
    id: number;
    parentId: number;
    blogId: number;
    type: number;
    state: number;
    commentatorName: string;
    content: string;
    createTime: string;
    child: CommentInfo[]
}
