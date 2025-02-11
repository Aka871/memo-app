// DOMが読み込まれた後に実行
document.addEventListener('DOMContentLoaded', () => {
    console.log('DOMContentLoaded発火');
    // メモ一覧を取得して表示
    fetchMemos();

    // 保存ボタンのクリックイベントを設定
    document.getElementById('createMemo').addEventListener('click', createMemo);
});

// メモ一覧を取得する関数
async function fetchMemos() {
    try {
        console.log('メモ一覧を取得中...');
        const response = await fetch('/memos');
        const memos = await response.json();
        console.log('取得したメモ:', memos);
        displayMemos(memos);
    } catch (error) {
        console.error('Error:', error);
    }
}

// メモ一覧を表示する関数
function displayMemos(memos) {
    // memosという要素を取得
    const memosDiv = document.getElementById('memos');
    // 既存の内容をクリア
    memosDiv.innerHTML = '';

    // 各メモをループで処理
    memos.forEach(memo => {
        // 新しいdiv要素を作成
        const memoElement = document.createElement('div');
        // クラス名を設定
        memoElement.className = 'memo';
        // メモの内容をHTML形式で設定
        memoElement.innerHTML = `
            <h3>${memo.title}</h3>
            <p>${memo.content}</p>
            <small>作成日時: ${new Date(memo.createdAt).toLocaleString()}</small>
            <!-- 削除ボタン。クリックでdeleteMemo関数を呼び出し -->
            <button onclick="deleteMemo(${memo.id})">削除</button>
        `;
        // 作成したメモ要素を追加
        memosDiv.appendChild(memoElement);
    });
}

// メモを作成する関数
async function createMemo() {
    const title = document.getElementById('memoTitle').value;
    const content = document.getElementById('memoContent').value;

    try {
        const response = await fetch('/memos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ title, content })
        });

        if (response.ok) {
            // フォームをクリア
            document.getElementById('memoTitle').value = '';
            document.getElementById('memoContent').value = '';
            // メモ一覧を更新
            fetchMemos();
        }
    } catch (error) {
        console.error('Error:', error);
    }
}

// メモを削除する関数
async function deleteMemo(id) {
    // 削除前の確認ダイアログを表示
    if (confirm('削除してもよろしいですか？')) {
        try {
            // APIを呼び出してメモを削除
            const response = await fetch(`/memos/${id}`, {
                method: 'DELETE'
            });
            // 削除が成功したら
            if (response.ok) {
                // メモ一覧を再取得して表示を更新
                fetchMemos();
            }
        } catch (error) {
            // エラーが発生した場合はコンソールに表示
            console.error('Error:', error);
        }
    }
}
