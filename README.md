# File Database API

## Descrição

Este projeto consiste em uma API para gerenciamento de arquivos em um banco de dados. Ele permite o upload e download de arquivos, além de suportar o upload de vários arquivos simultaneamente.

## Funcionalidades

- Upload de um arquivo único
- Download de um arquivo pelo seu ID
- Upload de múltiplos arquivos simultaneamente

## Endpoints

### Upload de Arquivo Único

Realiza o upload de um único arquivo.

- **URL**

  `/uploadFile`

- **Método**

  `POST`

- **Parâmetros de Requisição**

  `file` - Arquivo a ser enviado

- **Resposta de Sucesso**

  **Código:** 200 OK

  **Corpo:**

  ```json
  {
      "fileName": "nome_do_arquivo",
      "fileDownloadUri": "URL_para_baixar_o_arquivo",
      "fileType": "tipo_do_arquivo",
      "size": "tamanho_do_arquivo"
  }
  ```

### Upload de Múltiplos Arquivos

Realiza o upload de vários arquivos simultaneamente.

- **URL**

  `/uploadMultipleFiles`

- **Método**

  `POST`

- **Parâmetros de Requisição**

  `files` - Array de arquivos a serem enviados

- **Resposta de Sucesso**

  **Código:** 200 OK

  **Corpo:**

  ```json
  [
      {
          "fileName": "nome_do_arquivo1",
          "fileDownloadUri": "URL_para_baixar_o_arquivo1",
          "fileType": "tipo_do_arquivo1",
          "size": "tamanho_do_arquivo1"
      },
      {
          "fileName": "nome_do_arquivo2",
          "fileDownloadUri": "URL_para_baixar_o_arquivo2",
          "fileType": "tipo_do_arquivo2",
          "size": "tamanho_do_arquivo2"
      },
      ...
  ]
  ```

### Download de Arquivo Pelo ID

Realiza o download de um arquivo pelo seu ID.

- **URL**

  `/downloadFile/{fileId}`

- **Método**

  `GET`

- **Parâmetros de Requisição**

  `fileId` - ID do arquivo a ser baixado

- **Resposta de Sucesso**

  O arquivo será baixado automaticamente pelo navegador.

## Observações

Para realizar o upload e download de arquivos, é necessário enviar e receber os arquivos no formato `MultipartFile`. Certifique-se de usar as chaves corretas ao enviar as solicitações para os endpoints correspondentes.
