export interface ToDoListInfo {
    id?: number;
    matter?: string;
    tag?: string;
    state: number;
    type: number;
    startTime: string;
    endTime: string;
    createTime?: string;
}
