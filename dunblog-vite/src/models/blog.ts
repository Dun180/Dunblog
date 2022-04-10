export interface BlogProfile {
    id: number;
    title:string;
    count:string;
    content:string;
    categoryName:string;
    readTime:number;
    createTime:string;
}

export interface BlogProfiles{
    blogProfile:BlogProfile[];
}
