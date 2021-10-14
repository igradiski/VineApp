import PopConfirmCustom from "./PopConfirmCustom";
import { EditOutlined, DeleteOutlined } from '@ant-design/icons';

const TableUpdateDelete = (edit: (neke: any) => void, deleteItem: () => void, text: String) => {

    return (
        <div>
            <EditOutlined onClick={edit} />
            <br></br>
            {PopConfirmCustom(text, () => deleteItem(), <DeleteOutlined />)}
        </div>
    )

}
export default TableUpdateDelete;