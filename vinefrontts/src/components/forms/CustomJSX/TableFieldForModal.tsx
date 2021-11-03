import { FullscreenOutlined } from '@ant-design/icons';

const TableFieldForModal = (text:any,openBolestiModal: (record: any) => void) =>{
    return (
        <div> 
            {text}
            <FullscreenOutlined style={{float:"right"}}  onClick={openBolestiModal}/>
        </div>
    )
}

export default  TableFieldForModal; 
