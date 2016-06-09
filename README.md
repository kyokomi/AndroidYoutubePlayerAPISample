# AndroidYoutubePlayerAPISample
onBackPressedでonDestoryされてbackした先で動画が再生できない現象のワークアラウンド

### ワークアラウンド

onBackPressedをoverrideして、startActivtyを呼び出す。
intentの引数で今までの動画再生履歴（back機能を再現するのに必要なもの）を渡して、onBackPressed時にpopするイメージ。
引数で渡した動画再生履歴（stackList）が空になったら通常のonBackPressedで戻る

### 調査

| 手順                                                                                                                                  | OK/NG |
| ------------------------------------------------------------------------------------------------------------------ | --- |
| YoutubePlayerView -> startActivity -> 普通のActiity -> backキー -> YoutubePlayerView | 🙆  |
| YoutubePlayerView -> startActivity -> **YoutuberPlayerViewの存在するActivity** -> backキー -> YoutubePlayerView | 💀  |
| YoutubePlayerView -> homeボタン -> アプリへ戻る -> YoutubePlayerView | 🙆  |
| YoutubePlayerView -> startActivity -> 普通のActiity -> startActivity -> YoutubePlayerView ->  backキー -> 普通のActiity -> backキー -> YoutubePlayerView | 💀  |

おそらくonDestoryが呼ばれて何らかのYoutubePlayerViewのリソースが破棄されて、backで戻った先が壊れているようす。
