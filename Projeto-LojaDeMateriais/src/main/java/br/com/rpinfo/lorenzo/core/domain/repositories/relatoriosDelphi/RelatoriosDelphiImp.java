package br.com.rpinfo.lorenzo.core.domain.repositories.relatoriosDelphi;

import br.com.rpinfo.lorenzo.core.domain.model.entity.*;
import br.framework.classes.DataBase.QueryBuilder;
import br.framework.classes.DataBase.Repository;
import br.framework.interfaces.IConnection;

import java.util.Date;
import java.util.List;

public class RelatoriosDelphiImp extends Repository implements RelatoriosDelphiDao {
    public RelatoriosDelphiImp(IConnection connection) {
        super(connection, RelatoriosDelphiDao.class);
        this.getManager().setProcessNullToDefaultValues(false);
    }

    @Override
    public List<MovProdutosC> getEntradas(Date dataIni, Date dataFim) throws Exception {
        try {
            QueryBuilder sql = QueryBuilder.create(this.getConnection())
                    .select("*")
                    .from(MovProdutosC.class)
                    .innerJoin(Fornecedores.class, "mvpc_codentidade = forn_codigo")
                    .and("mvpc_status", "=", "N")
                    .and("mvpc_es", "=", "E")
                    .where("mvpc_datamvto").between(dataIni, dataFim)
                    .orderBy("mvpc_transacao");


            return this.getManager().queryFactory(sql.build(), MovProdutosC.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro buscando as movimentações de entrada na data especificada: " + e.getMessage());
        }
    }

    @Override
    public List<MovProdutosC> getSaidas(Date dataInicial, Date dataFinal) throws Exception {
        try {
            QueryBuilder sql = QueryBuilder.create(this.getConnection())
                    .select("*")
                    .from(MovProdutosC.class)
                    .innerJoin(Cliente.class, "mvpc_codentidade = clie_codigo")
                    .and("mvpc_status", "=", "N")
                    .and("mvpc_es", "=", "S")
                    .where("mvpc_datamvto").between(dataInicial, dataFinal)
                    .orderBy("mvpc_transacao");


            return this.getManager().queryFactory(sql.build(), MovProdutosC.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro buscando as movimentações de saída na data especificada: " + e.getMessage());
        }
    }

    @Override
    public List<MovProdutosC> getCancelados(Date dataInicial, Date dataFinal) throws Exception {
        try {
            QueryBuilder sql = QueryBuilder.create(this.getConnection())
                    .select("*")
                    .from(MovProdutosC.class)
                    .innerJoin(Cliente.class, "mvpc_codentidade = clie_codigo")
                    .and("mvpc_status", "=", "C")
                    .innerJoin(Fornecedores.class, "mvpc_codentidade = forn_codigo")
                    .where("mvpc_datamvto").between(dataInicial, dataFinal)
                    .orderBy("mvpc_transacao");


            return this.getManager().queryFactory(sql.build(), MovProdutosC.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro buscando as movimentações canceladas na data especificada: " + e.getMessage());
        }
    }

    @Override
    public List<VendComissoes> getVendedores(Date dataInicial, Date dataFinal) throws Exception {
        try {
            QueryBuilder sql = QueryBuilder.create(this.getConnection())
                    .select("vend_codigo, vend_nome, vend_comissao, mvpc_status AS status, mvpc_es AS es, SUM(mvpc_totaldcto) AS soma")
                    .from(MovProdutosC.class)
                    .innerJoin(Vendedores.class, "mvpc_vend_codigo = vend_codigo")
                    .and("mvpc_status", "=", "N")
                    .where("mvpc_datamvto").between(dataInicial, dataFinal)
                    .groupBy("mvpc_vend_codigo, vend_codigo, vend_nome, vend_comissao, status, es")
                    .orderBy("mvpc_vend_codigo");

            return this.getManager().queryFactory(sql.build(), VendComissoes.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro buscando as movimentações canceladas na data especificada: " + e.getMessage());
        }
    }

    @Override
    public List<PendFin> getRelPendFin(Date dataInicial, Date dataFinal, String status, String pagarReceber, String tipoEnt, Integer codEnt, String pendente) throws Exception {
        try {
            tipoEnt = String.valueOf(tipoEnt.charAt(0));
            if (pendente.equals("S")) {
                QueryBuilder sql = QueryBuilder.create(this.getConnection())
                        .select("*")
                        .from(PendFin.class)
                        .leftJoin(Cliente.class, "pfin_codentidade = clie_codigo")
                        .leftJoin(Fornecedores.class, "pfin_codentidade = forn_codigo")
                        .where("pfin_datavcto", ">=", dataInicial)
                        .and("pfin_datavcto", "<=", dataFinal)
                        .and("pfin_status", "=", status)
                        .and("pfin_tipo", "=", pagarReceber)
                        .and("pfin_catentidade", "=", tipoEnt)
                        .and("pfin_codentidade", "=", codEnt)
                        .and("pfin_databaixa").isNull()
                        .orderBy("pfin_transacao");
                return this.getManager().queryFactory(sql.build(), PendFin.class);
            } else if (pendente.equals("N")){
                QueryBuilder sql2 = QueryBuilder.create(this.getConnection())
                        .select("*")
                        .from(PendFin.class)
                        .leftJoin(Cliente.class, "pfin_codentidade = clie_codigo")
                        .leftJoin(Fornecedores.class, "pfin_codentidade = forn_codigo")
                        .where("pfin_datavcto", ">=", dataInicial)
                        .and("pfin_datavcto", "<=", dataFinal)
                        .and("pfin_status", "=", status)
                        .and("pfin_tipo", "=", pagarReceber)
                        .and("pfin_catentidade", "=", tipoEnt)
                        .and("pfin_codentidade", "=", codEnt)
                        .and("pfin_databaixa").isNotNull()
                        .orderBy("pfin_transacao");
                return this.getManager().queryFactory(sql2.build(), PendFin.class);
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Erro na query das pendências financeiras na data especificada: " + e.getMessage());
        }
    }

    @Override
    public List<ProdVend> getRelProdVend(Date dataInicial, Date dataFinal) throws Exception {
        try {
            QueryBuilder sql = QueryBuilder.create(this.getConnection())
                    .select("mvpd_qtde, prod_codigo, mvpc_es, " +
                            "prod_descricao, prod_marca, prod_preconvenda, " +
                            "mvpc_datamvto, SUM(mvpd_valortotal) AS total")
                    .from(Produtos.class)
                    .innerJoin(MovProdutosD.class, "prod_codigo = mvpd_prod_codigo")
                    .innerJoin(MovProdutosC.class, "mvpc_transacao = mvpd_transacao")
                    .where("mvpc_datamvto").between(dataInicial, dataFinal)
                    .and("mvpc_status", "=", "N")
                    .and("mvpc_es", "=", "S")
                    .groupBy("mvpd_qtde, prod_codigo, mvpc_es, " +
                            "prod_descricao, prod_marca, prod_preconvenda, " +
                            "mvpc_datamvto")
                    .orderBy("prod_codigo");

            return this.getManager().queryFactory(sql.build(), ProdVend.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro na query do relatório de produtos vendidos na data especificada: " + e.getMessage());
        }
    }

    @Override
    public MovProdutosC getRelTransacao(String transacao) throws Exception {
        try {
            MovProdutosC movC = null;
            QueryBuilder sql = QueryBuilder.create(this.getConnection())
                    .select("*")
                    .from(MovProdutosC.class)
                    //.innerJoin(MovProdutosD.class, "mvpc_transacao = mvpd_transacao")
                    .innerJoin(Cliente.class, "mvpc_codentidade = clie_codigo")
                    .innerJoin(Fornecedores.class, "mvpc_codentidade = forn_codigo")
                    .where("mvpc_transacao", "=", transacao);

            List<MovProdutosC> lista = this.getManager().queryFactory(sql.build(), MovProdutosC.class);
            if (!lista.isEmpty()) {
                movC = lista.get(0);
                List<MovProdutosD> itens = this.getListMovimentacoesD(transacao);
                movC.setItens(itens);
            }
            return movC;
        } catch (Exception e) {
            throw new RuntimeException("Erro na query de busca da transação: " + e.getMessage());
        }
    }

    private List<MovProdutosD> getListMovimentacoesD(String transacao) {
        try {
            QueryBuilder sql = QueryBuilder.create(this.getConnection())
                    .select("*")
                    .from(MovProdutosD.class)
                    .where("mvpd_transacao", "=", transacao);

            return this.getManager().queryFactory(sql.build(), MovProdutosD.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro buscando o detalhamento da lista de movimentações: " + e.getMessage());
        }
    }

    @Override
    public MovProdutosC getRelUnitVendedores(Integer codVendedor) throws Exception {
        try {
            MovProdutosC movC = null;
            QueryBuilder sql = QueryBuilder.create(this.getConnection())
                    .select("*")
                    .from(MovProdutosC.class)
                    .innerJoin(Cliente.class, "mvpc_codentidade = clie_codigo")
                    .innerJoin(Vendedores.class, "mvpc_vend_codigo = vend_codigo")
                    .where("mvpc_status", "=", "N")
                    .and("mvpc_vend_codigo", "=", codVendedor)
                    .orderBy("mvpc_vend_codigo");

            List<MovProdutosC> lista = this.getManager().queryFactory(sql.build(), MovProdutosC.class);
            if (!lista.isEmpty()) {
                movC = lista.get(0);
            }
            return movC;
        } catch (Exception e) {
            throw new RuntimeException("Erro na query de busca do relatório unitário de vendedores: " + e.getMessage());
        }
    }

    @Override
    public List<ProdutosMovimentacoes> getRelProdComprados(Date dataInicial, Date dataFinal) {
        try {
            ProdutosMovimentacoes prodMov = null;
            QueryBuilder sql = QueryBuilder.create(this.getConnection())
                    .select("mvpd_qtde AS qtde, prod_codigo AS codigo, mvpc_es, " +
                            "prod_descricao AS descricao, prod_marca AS marca, prod_precocompra AS precoCompra, " +
                            "mvpc_datamvto AS dataMvto, SUM(mvpd_valortotal) AS total")
                    .from(Produtos.class)
                    .innerJoin(MovProdutosD.class, "prod_codigo = mvpd_prod_codigo")
                    .innerJoin(MovProdutosC.class, "mvpc_transacao = mvpd_transacao")
                    .where("mvpc_datamvto").between(dataInicial, dataFinal)
                    .and("mvpc_status", "=", "N")
                    .and("mvpc_es", "=", "E")
                    .groupBy("mvpd_qtde, prod_codigo, mvpc_es, " +
                            "prod_descricao, prod_marca, prod_precocompra, " +
                            "mvpc_datamvto")
                    .orderBy("prod_codigo");

            List<ProdutosMovimentacoes> lista = this.getManager().queryFactory(sql.build(), ProdutosMovimentacoes.class);
            Double calculo = 0.0;
            if (!lista.isEmpty()) {
                for (int i = 0; i < lista.size(); i++) {
                    prodMov = lista.get(i);
                    calculo = prodMov.getPrecoCompra().getValue() * prodMov.getQtde().getValue();
                    prodMov.getTotal().setValue(calculo);
                    prodMov.getCalculo().setValue(calculo);
                    lista.set(i, prodMov);
                }
            }
            return lista;
        } catch (Exception e) {
            throw new RuntimeException("Erro na query de busca do relatório de produtos comprados: " + e.getMessage());
        }
    }

    @Override
    public List<PendFin> getRelDocBx(Date dataInicial, Date dataFinal) throws Exception {
        try {
            QueryBuilder sql = QueryBuilder.create(this.getConnection())
                    .select("*")
                    .from(PendFin.class)
                    .leftJoin(Cliente.class, "pfin_codentidade = clie_codigo")
                    .leftJoin(Fornecedores.class, "pfin_codentidade = forn_codigo")
                    .where("pfin_databaixa").between(dataInicial, dataFinal)
                    .and("pfin_status", "=", "N")
                    .orderBy("pfin_transacao");

            return this.getManager().queryFactory(sql.build(), PendFin.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro na query de busca do relatório unitário de vendedores: " + e.getMessage());
        }
    }
}
