package page;

public interface Paginable {

	/**
	 * 总记录数
	 * @return
	 */
	public int getTotal();
	
	/**
	 * 总页数
	 * @return
	 */
	public int getTotalPage();
	
	/**
	 * 每页条数
	 * @return
	 */
	public int getPageSize();
	
	/**
	 * 当前页号
	 * @return
	 */
	public int getPageNo();
	
	/**
	 * 是否第一页
	 * @return
	 */
	public boolean isFirstPage();
	
	/**
	 * 是否最后一页
	 * @return
	 */
	public boolean isLastPage();
	
	/**
	 * 下一页号
	 * @return
	 */
	public int getNextPage();
	
	/**
	 * 上一页号
	 * @return
	 */
	public int getPrePage();
}
