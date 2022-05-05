export interface BlogProfile {
    id: number;
    title: string;
    count: string;
    content: string;
    categoryName: string;
    categoryId: number;
    readTime: number;
    createTime: string;
    tagId:string;
    tagName:string;
}

export interface BlogProfiles{
    records: BlogProfile[];
    size: number;
    total: number;
}

