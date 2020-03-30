副檔 sh 為 linux；cmd 為 windows
先開 server，再開 Client

ls / 查根目錄的節點
stat 查看狀態, stat key
ls2 = ls + stat



create delete set get 增刪改查節點
※增
create key value：key 必需是個路徑，成功後 value 後面還會自動增加 stat
    value 可為空，使用兩個「"」或「'」即可
預設為持久化，就是重啟 ZK 時或退出 session 時還在
  有 -e 和 -s 可選項
  -e(ephemeral) 表示暫時的，也就是非持久化
  -s(sequential)，表示序號，ZK 會在 key 後增加10位的數字，為流水號(在 Windows 一用會導致整個用戶端關閉)
如果 key 存在就不能新增節點，但如果有 s 就可以，因為後面有流水號


※刪改查
delete key：key 要存在才行，只能刪空的節點
rmr：key 要存在才行，非空的節點也可刪
set key value：key 要存在才行
get key：查節點內容，key 要存在才行


close：關閉 session
quit：關閉 session 並將視窗關閉



※ACL (Access Control List)
CREATE: 可增
DELETE: 可刪
WRITE: 可改
READ：可查
ADMIN: 可設定權限

在 java 裡還有一個 ALL，表示以上全部都可以

權限認證方式：
world：預設值，全世界都能訪問
auth：使用當前用戶認證
digest：有帳號密碼才能訪問
ip：使用 IP 認證
x509：使用 X500 認證



※watch
監視節點有沒有變化，在 java 裡，如果設定為 true，會動態的取得值，如節點個數等…